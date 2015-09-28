package event.processing.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;
import event.processing.repo.QueryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestQueryRepository {

    @Autowired
    private QueryRepository repo;

    @Autowired
    private QueryFactory qf;

    private Query query;

    @Before
    public void before() throws IOException {

        String strQuery = Query.KEYWORD.CONDITION.getKeyword() + " DeviceInformation.property = 21 AND x.abc = 21 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = qf.parse(strQuery);

        repo.save(query);
    }

    @Test
    public void findAllQueries() {

        List<Query> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(1, allQueries.size());
        assertEquals(query.toString(), allQueries.get(0).toString());
    }

    @Test
    public void findOneQuey() {

        Query result = repo.findOne(query.toString());

        assertNotNull(result);
        assertEquals(query.toString(), result.toString());
    }

    @Test
    public void deleteQuery() {

        repo.delete(query);

        List<Query> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

}
