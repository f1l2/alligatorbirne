package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import event.processing.query.Query;

public class EPLBuilderSequence extends EPLBuilder {

    @Override
    public List<String> createEPL(List<Query> queries) {

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int i = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("select * from pattern [every ");

        for (Query query : queries) {
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
    public List<String> createEPL(Query query) {
        // NOT USED
        return null;
    }

    @Override
    public String createEPLPartWhereCondition() {
        // NOT USED
        return null;
    }
}
