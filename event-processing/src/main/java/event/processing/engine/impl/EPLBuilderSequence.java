package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import common.lang.query.QueryLang;
import common.lang.rule.model.Window;

public class EPLBuilderSequence extends EPLBuilder {

    private Window window = null;

    public EPLBuilderSequence(Window window) {
        super();
        this.window = window;
    }

    public List<String> createEPL(List<QueryLang> queries) {

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int i = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("select * from pattern [every ");
        if (window != null) {
            sb.append("(");
        }

        for (QueryLang query : queries) {
            if (i != 0) {
                sb.append(" -> ");
            }
            sb.append(alphabet[i]);
            sb.append("=Event(name = '");
            sb.append(query.getName());
            sb.append("')");
            i++;
        }

        if (window != null) {
            sb.append(") where timer:within(");
            sb.append(window.getValue());
            sb.append(" seconds)");
        }

        sb.append("]");

        List<String> epls = new ArrayList<String>();
        epls.add(sb.toString());
        return epls;

    }

    @Override
    public List<String> createEPL(QueryLang query) {
        // NOT USED
        return null;
    }

    @Override
    public String createEPLPartWhereCondition() {
        // NOT USED
        return null;
    }
}
