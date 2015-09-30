package event.processing.query;

import java.util.ArrayList;
import java.util.List;

import event.processing.query.model.Condition;
import event.processing.query.model.Evaluation;
import event.processing.query.model.Window;

public class Query {

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        CONDITION("CONDITION"),
        //
        FROM("FROM"),
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
        AND("AND", 2),
        //
        OR("OR", 2),
        //
        NOT("NOT", 1);

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
        SUM("SUM"),
        //
        AVG("AVG"),
        //
        COUNT("COUNT"),
        //
        MAX("MAX"),
        //
        MIN("MIN");

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

    private Condition condition;

    private List<String> domains = new ArrayList<String>();

    private Window window;

    public Condition getCondition() {
        return condition;
    }

    public static String addPrefix(String property) {
        if (isPropertyVariable(property)) {
            return Evaluation.PREFIX.concat(property);
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

}
