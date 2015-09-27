package event.processing.repo;

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
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestQueryRepository {

    @Autowired
    private QueryRepository repo;

    @Autowired
    private QueryFactory factory;

    private Query query;

    @Before
    public void before() throws IOException {

        query = factory.parse("condition d.device as device from DataSource as d where device.id = 5");

    }

    @Test
    public void saveQuery() {
        List<Query> allQueries = repo.findAll();

        assertNotNull(allQueries);

    }

    @Test
    public void findOneQuey() {

    }

    @Test
    public void findAllQueries() {

    }

    @Test
    public void deleteQuery() {

    }

}
