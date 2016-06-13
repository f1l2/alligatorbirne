package configuration.management.repo;

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

import common.transformer.Transformer;
import configuration.management.Application;
import configuration.management.model.QueryDLO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QueryRepositoryTest {

    @Autowired
    private QueryRepository repo;

    private QueryDLO query;

    @Before
    public void before() throws IOException {

        repo.deleteAll();

        String queryStr = "CONDITION property = 21 AND abc = 21 FROM Domain";
        String nameStr = "query1";

        query = new QueryDLO();
        query.setName(nameStr);
        query.setQuery(queryStr.toLowerCase());

        repo.save(query);
    }

    @Test
    public void findAllQueries() {

        List<QueryDLO> allQueries = Transformer.makeCollection(repo.findAll());

        assertNotNull(allQueries);
        assertEquals(1, allQueries.size());
        assertEquals(query.getName(), allQueries.get(0).getName());
    }

    @Test
    public void findOneQuery() {

        QueryDLO result = repo.findByName(query.getName());

        assertNotNull(result);
        assertEquals(query.getName(), result.getName());
    }

    @Test
    public void deleteQuery() {

        QueryDLO result = repo.findByName(query.getName());

        repo.delete(result);

        List<QueryDLO> allQueries = Transformer.makeCollection(repo.findAll());

        assertNotNull(allQueries);
        assertEquals(0, allQueries.size());
    }
}
