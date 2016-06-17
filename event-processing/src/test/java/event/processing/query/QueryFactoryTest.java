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

import common.lan.query.model.AggregateCondition;
import common.lan.query.model.CompositeCondition;
import common.lan.query.model.Evaluation;
import common.lan.query.model.SingleCondition;
import common.lang.query.QueryLang;
import common.lang.query.QueryLang.COMPARE_FUNCTION;
import event.processing.Application;
import event.processing.statement.QueryLangFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QueryFactoryTest {

    private String input;

    private QueryLang query;

    @Autowired
    private QueryLangFactory qf;

    @Test
    public void testCondition1() throws Exception {

        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " property = 23 AND 'abc' = 21 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);

        assertEquals("property = 23 and 'abc' = 21", ((CompositeCondition) query.getCondition()).generate());
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getSc().generate());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().generate());

        assertEquals(Evaluation.PREFIX_DEV_DATA + "property = 23 and 'abc' = 21", ((CompositeCondition) query.getCondition()).generateInclPrefix());
        assertEquals(Evaluation.PREFIX_DEV_DATA + "property = 23", ((CompositeCondition) query.getCondition()).getSc().generateInclPrefix());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().generateInclPrefix());

    }

    @Test
    public void testCondition2() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " property = property " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("property = property", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition3() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 = property " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = property", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition4() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 = 45 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 45", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testCondition5() throws Exception {

        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " name = 'device3'";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("name = 'device3'", ((SingleCondition) query.getCondition()).getEvaluation().generate());

    }

    @Test
    public void testCondition6() throws Exception {

        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " " + QueryLang.LOGIC_FUNCTION.NOT.getFunction() + " property = 23";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getCc().generate());
        assertEquals(QueryLang.LOGIC_FUNCTION.NOT, ((CompositeCondition) query.getCondition()).getCompositeFunction());

    }

    @Test
    public void testCondition7() throws Exception {

        input = "CONDITION property = 23 AND 'abc' = 21 AND 12 = 12 AND 12 = 12 FROM Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof CompositeCondition);
        assertEquals("property = 23", ((CompositeCondition) query.getCondition()).getSc().generate());
        assertEquals("'abc' = 21", ((CompositeCondition) query.getCondition()).getCc().getSc().generate());
        assertEquals("12 = 12 and 12 = 12", ((CompositeCondition) query.getCondition()).getCc().getCc().generate());
    }

    @Test
    public void testOperator1() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.EQUAL.getFunction() + " 14 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 14", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator2() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_GREATER.getFunction() + " 23 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 > 23", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator3() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_SMALLER.getFunction() + " 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 < 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator4() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21  " + COMPARE_FUNCTION.IS_GREATER_OR_EQUAL.getFunction() + " 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 >= 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testOperator5() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 " + COMPARE_FUNCTION.IS_SMALLER_OR_EQUAL.getFunction() + " 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 <= 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testWindows1() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain " + QueryLang.KEYWORD.TIME.getKeyword() + "(10)";
        query = test(input);

        assertNotNull(query);
        assertNotNull(query.getWindow());
        assertEquals("10", query.getWindow().getValue());
        assertEquals(QueryLang.KEYWORD.TIME, query.getWindow().getType());
    }

    @Test
    public void testWindows2() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain " + QueryLang.KEYWORD.LENGTH.getKeyword() + "(10)";
        query = test(input);

        assertNotNull(query);
        assertNotNull(query.getWindow());
        assertEquals("10", query.getWindow().getValue());
        assertEquals(QueryLang.KEYWORD.LENGTH, query.getWindow().getType());
    }

    @Test
    public void testDomain1() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain, DomainAB";
        query = test(input);

        assertNotNull(query);
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertEquals("21 = 79", ((SingleCondition) query.getCondition()).getEvaluation().generate());
        assertEquals("domain,domainab", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testAggregate1() throws Exception {
        input = QueryLang.KEYWORD.CONDITION.getKeyword() + " " + QueryLang.AGGREGATION_FUNCTION.SUM.getFunction() + "( abc ) = 79 " + QueryLang.KEYWORD.FROM.getKeyword() + " Domain";
        query = test(input);

        assertNotNull(query);
        assertEquals("sum(abc) = 79", query.getCondition().generate());
        assertEquals("sum(" + Evaluation.PREFIX_DEV_DATA + "abc) = 79", query.getCondition().generateInclPrefix());
        assertTrue(query.getCondition() instanceof SingleCondition);
        assertNotNull(((SingleCondition) query.getCondition()).getAggregateCondition());

        AggregateCondition aCondition = ((SingleCondition) query.getCondition()).getAggregateCondition();
        assertEquals(QueryLang.AGGREGATION_FUNCTION.SUM, aCondition.getAggregation());
        assertEquals("abc", aCondition.getProperty());
        assertEquals("79", aCondition.getValue());
        assertEquals(QueryLang.COMPARE_FUNCTION.EQUAL, aCondition.getOperator());
        assertEquals("domain", query.getDomains().stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testUseCase1() throws Exception {
        input = "CONDITION doorClosed=1 AND NOT isMovement=1 FROM OfficeRoom WIN:TIME(600)";
        query = test(input);

        input = "CONDITION doorCLosed=1 AND isMovement=1 FROM OfficeRoom WIN:TIME(600)";
        query = test(input);

    }

    @Test
    public void testUseCase2() throws Exception {
        input = "CONDITION AVG(performanceInPercent) > 90 FROM OfficeRoom WIN:TIME(600)";
        query = test(input);
    }

    @Test
    public void testUseCase31() throws Exception {
        input = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 0 FROM OfficeRoom WIN:TIME(10)";
        query = test(input);
    }

    @Test
    public void testUseCase32() throws Exception {
        input = "CONDITION doorClosed = 1 AND deskA = 1 AND deskB = 1 FROM OfficeRoom WIN:TIME(10)";
        query = test(input);
    }

    @Test
    public void testUseCase4() throws Exception {
        input = "CONDITION buildingEntered = 1 AND specialTreatment = 1 AND deskB = 1 FROM EntranceFloor";
        query = test(input);
    }

    @Test
    public void testUseCase5() throws Exception {
        input = "CONDITION AVG(waterUsageInLitre) > 10 FROM OfficeFloor WIN:TIME(3600)";
        query = test(input);
    }

    @Test
    public void testUseCase6() throws Exception {
        input = "CONDITION temperature > 32 AND smokeDetector = 1 FROM OfficeFloor WIN:TIME(120)";
        query = test(input);

    }

    private QueryLang test(String query) throws Exception {
        return qf.parse(query, "query");
    }
}
