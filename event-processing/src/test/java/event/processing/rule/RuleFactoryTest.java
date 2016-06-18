package event.processing.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import common.lang.rule.RuleLang;
import common.utilities.NormalizeString;
import event.processing.Application;
import event.processing.UCTest;
import event.processing.repo.QueryRepository;
import event.processing.statement.QueryLangFactory;
import event.processing.statement.RuleLangFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RuleFactoryTest {

    @Autowired
    private QueryRepository qr;

    @Autowired
    private QueryLangFactory qf;

    @Autowired
    private RuleLangFactory rf;

    private String input;

    private RuleLang rule;

    @Before
    public void before() throws IOException {

        String queryStr = "CONDITION id=5";

        QueryLang query = null;

        query = qf.parse(queryStr, "query1");
        qr.save(query);

        query = qf.parse(queryStr, "query2");
        qr.save(query);

        query = qf.parse(queryStr, "query3");
        qr.save(query);
    }

    @Test
    public void test1() throws IOException {

        input = "query1 TRIGGERS deviceName, domainName, cMName = 1";
        rule = test(input);

        assertNotNull(rule);
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertNotNull(rule.getReactions());
        assertEquals(1, rule.getReactions().size());
        assertEquals("devicename", rule.getReactions().get(0).getDeviceInformation());
        assertEquals("domainname", rule.getReactions().get(0).getDomainInformation());
        assertEquals("1", rule.getReactions().get(0).getConfigurationModification().getProperty("cmname"));
    }

    @Test
    public void test2() throws IOException {

        input = "query1 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2";

        rule = test(input);

        assertNotNull(rule);
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertNotNull(rule.getReactions());
        assertEquals(2, rule.getReactions().size());
        assertEquals("devicename1", rule.getReactions().get(0).getDeviceInformation());
        assertEquals("domainname1", rule.getReactions().get(0).getDomainInformation());
        assertEquals("1", rule.getReactions().get(0).getConfigurationModification().getProperty("cmname1"));

    }

    @Test
    public void test3() throws IOException {
        input = "query1 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2; deviceName3, domainName3, cMName3 = 3;";

        rule = test(input);

        assertNotNull(rule);
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertNotNull(rule.getReactions());
        assertEquals(3, rule.getReactions().size());
        assertEquals("devicename3", rule.getReactions().get(2).getDeviceInformation());
        assertEquals("domainname3", rule.getReactions().get(2).getDomainInformation());
        assertEquals("3", rule.getReactions().get(2).getConfigurationModification().getProperty("cmname3"));
    }

    @Test
    public void test4() throws IOException {

        input = "query1 -> query2 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2";

        rule = test(input);

        assertNotNull(rule);
        assertEquals(2, rule.getQueries().size());
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertEquals("query2", rule.getQueries().get(1).getName());
    }

    @Test
    public void test5() throws IOException {

        input = "query1 -> query2 -> query3 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2";

        rule = test(input);

        assertNotNull(rule);
        assertEquals(3, rule.getQueries().size());
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertEquals("query2", rule.getQueries().get(1).getName());
        assertEquals("query3", rule.getQueries().get(2).getName());
    }

    @Test
    public void test6() throws IOException {

        input = "query1 -> query2 -> query3 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2 WIN:TIME(10)";

        rule = test(NormalizeString.normalize(input));

        assertNotNull(rule);
        assertEquals(3, rule.getQueries().size());
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertEquals("query2", rule.getQueries().get(1).getName());
        assertEquals("query3", rule.getQueries().get(2).getName());
        assertEquals("10", rule.getWindow().getValue());
    }

    @Test
    public void test7() throws IOException {

        input = "query1 -> query2 -> query3 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2 WIN:TIME(10)";

        rule = test(NormalizeString.normalize(input));

        assertNotNull(rule);
        assertEquals(3, rule.getQueries().size());
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertEquals("query2", rule.getQueries().get(1).getName());
        assertEquals("query3", rule.getQueries().get(2).getName());
        assertEquals("10", rule.getWindow().getValue());
        assertFalse(rule.getNegations().contains("query1"));
        assertFalse(rule.getNegations().contains("query2"));
        assertFalse(rule.getNegations().contains("query3"));
    }

    @Test
    public void test8() throws IOException {

        input = "NOT query1 -> query2 -> NOT query3 TRIGGERS deviceName1, domainName1, cMName1 = 1; deviceName2, domainName2, cMName2 = 2 WIN:TIME(10)";

        rule = test(NormalizeString.normalize(input));

        assertNotNull(rule);
        assertEquals(3, rule.getQueries().size());
        assertEquals("query1", rule.getQueries().get(0).getName());
        assertEquals("query2", rule.getQueries().get(1).getName());
        assertEquals("query3", rule.getQueries().get(2).getName());
        assertEquals("10", rule.getWindow().getValue());
        assertTrue(rule.getNegations().contains("query1"));
        assertFalse(rule.getNegations().contains("query2"));
        assertTrue(rule.getNegations().contains("query3"));
    }

    private RuleLang test(String rule) throws IOException {

        RuleLang r = rf.parse(rule);

        List<QueryLang> queries = new ArrayList<QueryLang>();
        for (String queryName : r.getQueryNames()) {
            QueryLang query = qr.findOne(queryName);

            if (null != query) {
                queries.add(query);
            }
        }
        r.setQueries(queries);

        return r;
    }

    @Test
    public void testUseCases() throws Exception {
        // test(UCTest.UC1_RULE1);
        test(UCTest.UC2_RULE1);
        // test(UCTest.UC2_RULE1);
        // test(UCTest.UC3_RULE1);
        // test(UCTest.UC3_RULE2);
        // test(UCTest.UC4_RULE1);
        // test(UCTest.UC5_RULE1);
        // test(UCTest.UC6_RULE1);
    }
}
