package event.processing.query;

import java.util.ArrayList;
import java.util.List;

public class Query {

    /**
     * Keywords
     */
    public static final String KEYWORD_CONDITION = "CONDITION";

    public static final String KEYWORD_FROM = "FROM";

    public static final String KEYWORD_WIN = "WIN.";

    public static final String KEYWORD_TIME = "TIME";

    public static final String KEYWORD_LENGTH = "LENGTH";

    public static final String AGG_SUM = "SUM";

    public static final String AGG_AVG = "AVG";

    public static final String AGG_COUNT = "COUNT";

    public static final String AGG_MAX = "MAX";

    public static final String AVG_MIN = "MIN";

    public static final String LOGIC_AND = "AND";

    /**
     * Operators
     */
    public static final String OPERATOR_EQUAL = "=";

    public static final String OPERATOR_IS_GREATER = ">";

    public static final String OPERATOR_IS_SMALLER = "<";

    public static final String OPERATOR_IS_GREATER_OR_EQUAL = ">=";

    public static final String OPERATOR_IS_SMALLER_OR_EQUAL = "<=";

    /**
     * Parts
     */

    private String condition;

    private String domainList;

    private List<String> domain = new ArrayList<String>();

    private String compareLogic;

    private List<String> compare = new ArrayList<String>();

    private List<String> aggregate = new ArrayList<String>();

    private List<String> property = new ArrayList<String>();

    private String window;

    private String windowValue;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDomainList() {
        return domainList;
    }

    public void setDomainList(String domainList) {
        this.domainList = domainList;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public String getCompareLogic() {
        return compareLogic;
    }

    public void setCompareLogic(String compareLogic) {
        this.compareLogic = compareLogic;
    }

    public List<String> getCompare() {
        return compare;
    }

    public void setCompare(List<String> compare) {
        this.compare = compare;
    }

    public List<String> getAggregate() {
        return aggregate;
    }

    public void setAggregate(List<String> aggregate) {
        this.aggregate = aggregate;
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getWindowValue() {
        return windowValue;
    }

    public void setWindowValue(String windowValue) {
        this.windowValue = windowValue;
    }

    @Override
    public String toString() {
        return "Query [condition=" + condition + ", domainList=" + domainList + ", domain=" + domain + ", compareLogic=" + compareLogic + ", compare=" + compare + ", aggregate=" + aggregate
                + ", property=" + property + ", window=" + window + ", windowValue=" + windowValue + "]";
    }

}
