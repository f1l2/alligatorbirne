package event.processing.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.After;
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
public class QueryRepositoryTest {

    @Autowired
    private QueryRepository repo;

    @Autowired
    private QueryFactory qf;

    private Query query;

    @Before
    public void before() throws IOException {

        String input = Query.KEYWORD.CONDITION.getKeyword() + " property = 21 AND abc = 21 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = qf.parse(input);

        repo.save("test query", query);
    }

    @After
    public void after() {
        repo.delete("test query");
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

        Query result = repo.findOne("test query");

        assertNotNull(result);
        assertEquals(query.toString(), result.toString());
    }

    @Test
    public void deleteQuery() {

        repo.delete("test query");

        List<Query> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

}
