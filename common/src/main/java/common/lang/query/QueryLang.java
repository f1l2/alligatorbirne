package common.lang.query;

import java.util.ArrayList;
import java.util.List;

import common.lang.query.model.AggregateCondition;
import common.lang.query.model.CompositeCondition;
import common.lang.query.model.Condition;
import common.lang.query.model.Evaluation;
import common.lang.query.model.SingleCondition;
import common.lang.query.model.Window;
import common.utilities.NormalizeString;

public class QueryLang {

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        CONDITION("condition"),
        //
        FROM("from"),
        //
        TIME("win:time"),
        //
        LENGTH("win:length");

        private String keyword;

        KEYWORD(String keyword) {
            this.setKeyword(keyword);
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public static KEYWORD findByKeyword(String keyword) {
            for (KEYWORD item : KEYWORD.values()) {
                if (item.getKeyword().equals(keyword)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * Enum Logic Fucntions
     */
    public static enum LOGIC_FUNCTION {
        AND("and", 2),
        //
        OR("or", 2),
        //
        NOT("not", 1);

        private String function;

        private int numberOperands;

        LOGIC_FUNCTION(String symbol, int numberOperands) {
            this.setFunction(symbol);
            this.setNumberOperand(numberOperands);
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public int getNumberOperand() {
            return numberOperands;
        }

        public void setNumberOperand(int numberOperand) {
            this.numberOperands = numberOperand;
        }

        public static LOGIC_FUNCTION findByFunction(String function) {
            for (LOGIC_FUNCTION item : LOGIC_FUNCTION.values()) {
                if (item.getFunction().equals(function)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * Enum Aggregation Functions
     */
    public static enum AGGREGATION_FUNCTION {
        SUM("sum"),
        //
        AVG("avg"),
        //
        COUNT("count"),
        //
        MAX("max"),
        //
        MIN("min");

        private String function;

        AGGREGATION_FUNCTION(String function) {
            this.setFunction(function);
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public static AGGREGATION_FUNCTION findByFunction(String function) {
            for (AGGREGATION_FUNCTION item : AGGREGATION_FUNCTION.values()) {
                if (item.getFunction().equals(function)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * Enum Compare Functions
     */
    public static enum COMPARE_FUNCTION {

        EQUAL("="),
        //
        IS_GREATER(">"),
        //
        IS_GREATER_OR_EQUAL(">="),
        //
        IS_SMALLER("<"),
        //
        IS_SMALLER_OR_EQUAL("<=");

        private String function;

        COMPARE_FUNCTION(String function) {
            this.setFunction(function);
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public static COMPARE_FUNCTION findByFunction(String function) {
            for (COMPARE_FUNCTION item : COMPARE_FUNCTION.values()) {
                if (item.getFunction().equals(function)) {
                    return item;
                }
            }
            return null;
        }
    }

    private String name;

    private String nativeQuery;

    private Condition condition;

    private List<String> domains = new ArrayList<String>();

    private Window window;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeQuery() {
        return nativeQuery;
    }

    public void setNativeQuery(String nativeQuery) {
        this.nativeQuery = nativeQuery;
    }

    public Condition getCondition() {
        return condition;
    }

    public static String addPrefix(String property) {
        if (isPropertyVariable(property)) {

            property = NormalizeString.normalize(property);

            switch (property.toLowerCase()) {
            case "name":
            case "id":
                return Evaluation.PREFIX_DEV_INFO.concat(property);

            case "value":
            default:
                return Evaluation.PREFIX_DEV_DATA.concat(property);
            }
        } else {
            return property;
        }
    }

    public static Boolean isPropertyVariable(String property) {
        return !(property.matches("^[0-9]+$") || (property.matches("^'.+'$")));
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public List<AggregateCondition> collectAggregateCondition() {

        List<AggregateCondition> aConditions = new ArrayList<AggregateCondition>();
        if (condition instanceof SingleCondition) {
            collectAggregateCondition((SingleCondition) condition, aConditions);
        } else if (condition instanceof CompositeCondition) {
            collectAggregateCondition((CompositeCondition) condition, aConditions);
        }
        return aConditions;
    }

    private void collectAggregateCondition(CompositeCondition condition, List<AggregateCondition> collection) {
        if (condition.getCc() != null)
            collectAggregateCondition(condition.getCc(), collection);
        if (condition.getSc() != null)
            collectAggregateCondition(condition.getSc(), collection);
    }

    private void collectAggregateCondition(SingleCondition condition, List<AggregateCondition> collection) {

        if (condition.getAggregateCondition() != null) {
            collection.add(condition.getAggregateCondition());
        }
    }
}
