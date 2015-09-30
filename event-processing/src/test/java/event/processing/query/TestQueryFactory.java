package event.processing.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;
import event.processing.query.Query.COMPARE_FUNCTION;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestQueryFactory {

    private String input;

    private Query query;

    @Autowired
    private QueryFactory qf;

    @Test
    public void testCondition1() throws Exception {

        input = Query.KEYWORD.CONDITION.getKeyword() + " property = 23 AND 'abc' = 21 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);

        assertEquals("property = 23 AND 'abc' = 21", ((CompositeCondition) query.getCondition()).generate());
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getSc().generate());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().generate());

        assertEquals(Evaluation.PREFIX + "property = 23 AND 'abc' = 21", ((CompositeCondition) query.getCondition()).generateInclPrefix());
        assertEquals(Evaluation.PREFIX + "property = 23", ((CompositeCondition) query.getCondition()).getSc().generateInclPrefix());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().generateInclPrefix());

    }

    @Test
    public void testCondition2() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " property = property " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("property = property", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition3() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 = property " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = property", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition4() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 45 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 45", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition5() throws Exception {

        input = Query.KEYWORD.CONDITION.getKeyword() + " name = 'device3'";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("name = 'device3'", ((SingleCondition) query.getCondition()).getEvaluation().generate());

    }

    @Test
    public void testCondition6() throws Exception {

        input = Query.KEYWORD.CONDITION.getKeyword() + " " + Query.LOGIC_FUNCTION.NOT.getFunction() + " property = 23";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getCc().generate());
        assertEquals(Query.LOGIC_FUNCTION.NOT, ((CompositeCondition) query.getCondition()).getCompositeFunction());

    }

    @Test
    public void testCondition7() throws Exception {

        input = "CONDITION property = 23 AND 'abc' = 21 AND 12 = 12 AND 12 = 12 FROM Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getSc().generate());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().getSc().generate());
        assertEquals("12 = 12 AND 12 = 12", ((CompositeCondition) query.getCondition()).getCc().getCc().generate());
    }

    @Test
    public void testOperator1() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.EQUAL.getFunction() + " 14 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 14", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator2() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_GREATER.getFunction() + " 23 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 > 23", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator3() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_SMALLER.getFunction() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 < 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator4() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21  " + COMPARE_FUNCTION.IS_GREATER_OR_EQUAL.getFunction() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 >= 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator5() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_SMALLER_OR_EQUAL.getFunction() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 <= 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testWindows1() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain " + Query.KEYWORD.WIN.getKeyword() + "." + Query.KEYWORD.TIME.getKeyword() + "(10)";
        query = test(input);
    }

    @Test
    public void testWindows2() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain " + Query.KEYWORD.WIN.getKeyword() + "." + Query.KEYWORD.LENGTH.getKeyword() + "(10)";
        query = test(input);
    }

    @Test
    public void testDomain1() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain, DomainAB";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("Domain,DomainAB", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testAggregate1() throws Exception {
        input = Query.KEYWORD.CONDITION.getKeyword() + " " + Query.AGGREGATION_FUNCTION.SUM.getFunction() + "( abc ) = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);
    }

    private Query test(String query) throws Exception {
        return qf.parse(query);
    }
}
