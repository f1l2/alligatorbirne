package event.processing.query.language;

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
public class TestQueryFactory {

    private String query;

    @Autowired
    private QueryFactory qf;

    @Test
    public void testCondition1() throws Exception {
        query = Query.KEYWORD_CONDITION + " DeviceInformation.property = 21 AND x.abc = 21 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testCondition2() throws Exception {
        query = Query.KEYWORD_CONDITION + " DeviceInformation.property = DeviceInformation.property " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testCondition3() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 = DeviceInformation.property " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testCondition4() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 = 45 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testOperator1() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 " + Query.OPERATOR_EQUAL + " 14 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testOperator2() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 " + Query.OPERATOR_IS_BIGGER + " 23 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testOperator3() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 " + Query.OPERATOR_IS_SMALLER + " 79 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testOperator4() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21  " + Query.OPERATOR_IS_BIGGER_OR_EQUAL + " 79 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testOperator5() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 " + Query.OPERATOR_IS_SMALLER_OR_EQUAL + " 79 " + Query.KEYWORD_FROM + " Domain";
        test(query);
    }

    @Test
    public void testWindows1() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 = 79 " + Query.KEYWORD_FROM + " Domain " + Query.KEYWORD_WIN + Query.KEYWORD_TIME + "(10)";
        test(query);
    }

    @Test
    public void testWindows2() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 = 79 " + Query.KEYWORD_FROM + " Domain " + Query.KEYWORD_WIN + Query.KEYWORD_LENGTH + "(10)";
        test(query);
    }

    @Test
    public void testDomain1() throws Exception {
        query = Query.KEYWORD_CONDITION + " 21 = 79 " + Query.KEYWORD_FROM + " Domain, DomainAB";
        test(query);
    }

    @Test
    public void testAggregate1() throws Exception {
        query = Query.KEYWORD_CONDITION + " " + Query.AGG_AVG + "( 21 ) = 79 " + Query.KEYWORD_FROM + " Domain";
        test(query);

    }

    private void test(String query) throws Exception {
        qf.parse(query);
    }
}
