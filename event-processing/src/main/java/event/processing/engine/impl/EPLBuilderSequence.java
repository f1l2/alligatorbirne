package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import common.lang.query.QueryLang;

public class EPLBuilderSequence extends EPLBuilder {

    @Override
    public List<String> createEPL(List<QueryLang> queries) {

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int i = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("select * from pattern [every ");

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
