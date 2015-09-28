package event.processing.query;

import java.util.ArrayList;
import java.util.List;

public class Query {

    /**
     * Enum Keywords
     * 
     *
     */
    public static enum KEYWORD {
        CONDITION("CONDITION"),
        //
        FROM("FROM"),
        //
        WIN("WIN."),
        //
        TIME("TIME"),
        //
        LENGTH("LENGTH");

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

    }

    /**
     * Keyword Logic Symbols
     *
     */
    public static enum LOGIC_SYMBOL {
        AND("AND", 2),
        //
        OR("OR", 2),
        //
        NOT("NOT", 1);

        private String symbol;

        private int numberOperand;

        LOGIC_SYMBOL(String symbol, int numberOperand) {
            this.setSymbol(symbol);
            this.setNumberOperand(numberOperand);
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getNumberOperand() {
            return numberOperand;
        }

        public void setNumberOperand(int numberOperand) {
            this.numberOperand = numberOperand;
        }
    }

    /**
     * Enum Aggregator
     */

    public static enum AGGREGATOR {
        SUM("SUM"),
        //
        AVG("AVG"),
        //
        COUNT("COUNT"),
        //
        MAX("MAX"),
        //
        MIN("<=");

        private String sign;

        AGGREGATOR(String sign) {

            this.setSign(sign);

        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    /**
     * Enum Operator
     */

    public static enum OPERATOR {
        EQUAL("="),
        //
        IS_GREATER(">"),
        //
        IS_GREATER_OR_EQUAL(">="),
        //
        IS_SMALLER("<"),
        //
        IS_SMALLER_OR_EQUAL("<=");

        private String sign;

        OPERATOR(String sign) {

            this.setSign(sign);

        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    /**
     * Parts
     */
    private List<String> domains = new ArrayList<String>();

    private List<String> logicLinks = new ArrayList<String>();

    private List<String> compares = new ArrayList<String>();

    private List<String> property = new ArrayList<String>();

    private List<String> aggregateCompares = new ArrayList<String>();

    private List<String> aggregates = new ArrayList<String>();

    public List<String> getCompares() {
        return compares;
    }

    public void setCompares(List<String> compares) {
        this.compares = compares;
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }

    public List<String> getAggregateCompares() {
        return aggregateCompares;
    }

    public void setAggregateCompares(List<String> aggregateCompares) {
        this.aggregateCompares = aggregateCompares;
    }

    public List<String> getAggregates() {
        return aggregates;
    }

    public void setAggregates(List<String> aggregates) {
        this.aggregates = aggregates;
    }

    private String window;

    private String windowValue;

    public String getWindowValue() {
        return windowValue;
    }

    public void setWindowValue(String windowValue) {
        this.windowValue = windowValue;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getLogicLinks() {
        return logicLinks;
    }

    public void setLogicLinks(List<String> logicLinks) {
        this.logicLinks = logicLinks;
    }

    @Override
    public String toString() {
        return "Query [domains=" + domains + ", compares=" + compares + ", property=" + property + ", logicLinks=" + logicLinks + ", aggregateCompares=" + aggregateCompares + ", aggregates="
                + aggregates + ", window=" + window + ", windowValue=" + windowValue + "]";
    }

}
