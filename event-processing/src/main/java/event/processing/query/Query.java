package event.processing.query;


public class Query {
	
	/*
	 * KEYWORD
	 */
	public static final String KEYWORD_SELECT = "SELECT";	
	public static final String KEYWORD_FROM = "FROM";
	
	/*
	 * Operator
	 */
	public static final String OPERATOR_EQUAL = "=";
	public static final String OPERATOR_IS_BIGGER = ">";
	public static final String OPERATOR_IS_SMALLER = "<";
	public static final String OPERATOR_IS_BIGGER_OR_EQUAL = ">=";
	public static final String OPERATOR_IS_SMALLER_OR_EQUAL = "<=";
	
	private String condition;
	
	private String[] domainList;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String[] getDomainList() {
		return domainList;
	}

	public void setDomainList(String[] domainList) {
		this.domainList = domainList;
	}
	
	
	public Query(String condition, String[] domainList) {
		this.condition = condition;
		this.domainList = domainList;
	}
	
}