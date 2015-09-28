package event.processing.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.Application;
import event.processing.query.Query.OPERATOR;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestQueryFactory {

    private String query;

    @Autowired
    private QueryFactory qf;

    @Test
    public void testCondition1() throws Exception {

        query = Query.KEYWORD.CONDITION.getKeyword() + " property = 21 AND abc = 21 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testCondition2() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " property = property " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testCondition3() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 = property " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testCondition4() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 45 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testOperator1() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + OPERATOR.EQUAL.getSign() + " 14 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testOperator2() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + OPERATOR.IS_GREATER.getSign() + " 23 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testOperator3() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + OPERATOR.IS_SMALLER.getSign() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testOperator4() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21  " + OPERATOR.IS_GREATER_OR_EQUAL.getSign() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testOperator5() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 " + OPERATOR.IS_SMALLER_OR_EQUAL.getSign() + " 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);
    }

    @Test
    public void testWindows1() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain " + Query.KEYWORD.WIN.getKeyword() + Query.KEYWORD.TIME.getKeyword() + "(10)";
        test(query);
    }

    @Test
    public void testWindows2() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain " + Query.KEYWORD.WIN.getKeyword() + Query.KEYWORD.LENGTH.getKeyword() + "(10)";
        test(query);
    }

    @Test
    public void testDomain1() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " 21 = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain, DomainAB";
        test(query);
    }

    @Test
    public void testAggregate1() throws Exception {
        query = Query.KEYWORD.CONDITION.getKeyword() + " " + Query.AGGREGATOR.SUM.getSign() + "( abc ) = 79 " + Query.KEYWORD.FROM.getKeyword() + " Domain";
        test(query);

    }

    private void test(String query) throws Exception {

        qf.parse(query);
    }
}
