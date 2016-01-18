package event.processing.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.lang.query.QueryLang;
import event.processing.Application;
import event.processing.repo.QueryRepository;
import event.processing.statement.QueryLangFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QueryRepositoryTest {

    @Autowired
    private QueryRepository repo;

    @Autowired
    private QueryLangFactory qf;

    private QueryLang query;

    @Before
    public void before() throws IOException {

        repo.reset();

        String input = QueryLang.KEYWORD.CONDITION.getKeyword() + " property = 21 AND abc = 21 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = qf.parse(input, "test query");

        repo.save(query);
    }

    @Test
    public void findAllQueries() {

        List<QueryLang> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(1, allQueries.size());
        assertEquals(query.toString(), allQueries.get(0).toString());
    }

    @Test
    public void findAllQueries2() {

        QueryLang query1 = new QueryLang();
        query1.setName("test query 123");

        QueryLang query2 = new QueryLang();
        query2.setName("test query");

        List<QueryLang> queries = new ArrayList<QueryLang>();
        queries.add(query1);

        List<QueryLang> allQueries = repo.findAllByQueries(queries);
        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

        queries.add(query2);

        allQueries = repo.findAllByQueries(queries);

        assertNotNull(allQueries);
        assertEquals(1, allQueries.size());
        assertEquals(query.toString(), allQueries.get(0).toString());
    }

    @Test
    public void findOneQuery() {

        QueryLang result = repo.findOne("test query");

        assertNotNull(result);
        assertEquals(query.toString(), result.toString());
    }

    @Test
    public void deleteQuery() {

        repo.delete("test query");

        List<QueryLang> allQueries = repo.findAll();

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());

    }

}
