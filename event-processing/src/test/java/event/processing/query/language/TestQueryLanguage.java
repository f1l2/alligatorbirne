package event.processing.query.language;

import org.junit.Test;

import event.processing.query.Query;
import event.processing.query.QueryFactory;

public class TestQueryLanguage {
	private static String query;
	
	@Test 
	public void testCondition1() throws Exception {
		query = Query.KEYWORD_SELECT  + " DeviceInformation.property = 21 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testCondition2() throws Exception {
		query = Query.KEYWORD_SELECT + " DeviceInformation.property = DeviceInformation.property " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testCondition3() throws Exception {
		query = Query.KEYWORD_SELECT  + " 21 = DeviceInformation.property " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	
	@Test 
	public void testCondition4() throws Exception {
		query = Query.KEYWORD_SELECT + " 21 = 45 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testOperator1() throws Exception {
		query = Query.KEYWORD_SELECT  + " 21 " + Query.OPERATOR_EQUAL + " 14 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testOperator2() throws Exception {
		query = Query.KEYWORD_SELECT  + " 21 " + Query.OPERATOR_IS_BIGGER + " 23 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testOperator3() throws Exception {
		query = Query.KEYWORD_SELECT + " 21 " + Query.OPERATOR_IS_SMALLER + " 79 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testOperator4() throws Exception {
		query = Query.KEYWORD_SELECT  + " 21  " + Query.OPERATOR_IS_BIGGER_OR_EQUAL + " 79 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	@Test 
	public void testOperator5() throws Exception {
		query = Query.KEYWORD_SELECT + " 21 " + Query.OPERATOR_IS_SMALLER_OR_EQUAL + " 79 " + Query.KEYWORD_FROM + " Domain";
		test(query);
	}
	
	
	
	private void test(String query) throws Exception {

			Query queryObject = new QueryFactory().createQuery(query);
			System.out.println(queryObject.getCondition());
	}
}
