package event.processing.query;

import java.util.List;

public class QueryLogicLink {

    private String logicLink;

    private Query.LOGIC_SYMBOL symbol;

    private List<String> compares;

    public Query.LOGIC_SYMBOL getSymbol() {
        return symbol;
    }

    public void setSymbol(Query.LOGIC_SYMBOL symbol) {
        this.symbol = symbol;
    }

    public List<String> getCompares() {
        return compares;
    }

    public void setCompares(List<String> compares) {
        this.compares = compares;
    }

    public String getLogicLink() {
        return logicLink;
    }

    public void setLogicLink(String logicLink) {
        this.logicLink = logicLink;
    }

}
