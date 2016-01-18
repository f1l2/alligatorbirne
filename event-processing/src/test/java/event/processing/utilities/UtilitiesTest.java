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

import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;
import event.processing.Application;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;

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

        QueryLang query1 = new QueryLang();
        query1.setName("query1");

        qr.save(query1);

        QueryLang query2 = new QueryLang();
        query2.setName("query2");

        qr.save(query2);

        RuleLang rule = new RuleLang();
        rule.addQueryName("query1");
        rule.addQueryName("query2");

        rule = RepoUtilities.findQueriesToQueryNames(rule, qr);

        assertNotNull(rule);
        assertEquals(2, rule.getQueries().size());

    }

    @Test
    public void filterActiveQueries() throws Exception {

        qr.reset();

        QueryLang query1 = new QueryLang();
        query1.setName("query1");

        qr.save(query1);

        QueryLang query2 = new QueryLang();
        query2.setName("query2");

        qr.save(query2);

        RuleLang rule = new RuleLang();
        rule.addQueryName("query1");

        rule = RepoUtilities.findQueriesToQueryNames(rule, qr);
        rule.setIsActivated(true);

        rr.save("rule1", rule);

        /**
         * List of queries is empty
         */

        List<QueryLang> queries = new ArrayList<QueryLang>();

        List<QueryLang> notActiveQueries = RepoUtilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(0, notActiveQueries.size());

        /**
         * Add query to list. Query is not active.
         */

        queries.add(query2);

        notActiveQueries = RepoUtilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(1, notActiveQueries.size());

        /**
         * Add another query. Query is not part of an active rule.
         */

        queries.add(query1);

        notActiveQueries = RepoUtilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(1, notActiveQueries.size());

        /**
         * Deactivate rule.
         */

        rule.setIsActivated(false);
        rr.save("rule1", rule);

        notActiveQueries = RepoUtilities.filterActiveQueries(queries, rr);

        assertNotNull(notActiveQueries);
        assertEquals(2, notActiveQueries.size());

    }
}
