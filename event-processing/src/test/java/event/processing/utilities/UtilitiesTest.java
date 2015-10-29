package event.processing.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;
import event.processing.query.Query;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UtilitiesTest {

    @Autowired
    private QueryRepository qr;

    @Autowired
    private RuleRepository rr;

    @Test
    public void findQueriesToQueryNames() throws Exception {

        qr.reset();

        Query query1 = new Query();
        query1.setName("query1");

        qr.save(query1);

        Query query2 = new Query();
        query2.setName("query2");

        qr.save(query2);

        Rule rule = new Rule();
        rule.addQueryName("query1");
        rule.addQueryName("query2");

        rule = Utilities.findQueriesToQueryNames(rule, qr);

        assertNotNull(rule);
        assertEquals(2, rule.getQueries().size());

    }

    @Test
    public void filterActiveQueries() throws Exception {

        qr.reset();

        Query query1 = new Query();
        query1.setName("query1");

        qr.save(query1);

        Query query2 = new Query();
        query2.setName("query2");

        qr.save(query2);

        Rule rule = new Rule();
        rule.addQueryName("query1");

        rule = Utilities.findQueriesToQueryNames(rule, qr);
        rule.setIsActivated(true);

        rr.save("rule1", rule);

        /**
         * List of queries is empty
         */

        List<Query> queries = new ArrayList<Query>();

        List<Query> notActiveQueries = Utilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(0, notActiveQueries.size());

        /**
         * Add query to list. Query is not active.
         */

        queries.add(query2);

        notActiveQueries = Utilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(1, notActiveQueries.size());

        /**
         * Add another query. Query is not part of an active rule.
         */

        queries.add(query1);

        notActiveQueries = Utilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(1, notActiveQueries.size());

        /**
         * Deactivate rule.
         */

        rule.setIsActivated(false);
        rr.save("rule1", rule);

        notActiveQueries = Utilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(2, notActiveQueries.size());

    }
}
