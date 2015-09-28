package event.processing.query;

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

        public static LOGIC_SYMBOL findBySign(String sign) {
            for (LOGIC_SYMBOL item : LOGIC_SYMBOL.values()) {
                if (item.getSymbol().equals(sign)) {
                    return item;
                }
            }
            return null;
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

        public static AGGREGATOR findBySign(String sign) {
            for (AGGREGATOR item : AGGREGATOR.values()) {
                if (item.getSign().equals(sign)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * Enum LOGIC_SYMBOL
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

        public static OPERATOR findBySign(String sign) {
            for (OPERATOR item : OPERATOR.values()) {
                if (item.getSign().equals(sign)) {
                    return item;
                }
            }
            return null;
        }
    }

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

}
