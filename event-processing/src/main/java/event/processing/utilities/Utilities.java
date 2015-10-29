package event.processing.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import event.processing.query.Query;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;

public class Utilities {

    public static Rule findQueriesToQueryNames(Rule r, QueryRepository qr) throws Exception {

        List<Query> queries = new ArrayList<Query>();
        for (String queryName : r.getQueryNames()) {
            Query query = qr.findOne(queryName);

            if (null != query) {
                queries.add(query);
            } else {
                throw new Exception();
            }
        }

        r.setQueries(queries);

        return r;
    }

    public static List<Query> filterActiveQueries(List<Query> queries, RuleRepository rr) {

        Set<String> activeQueries = new HashSet<String>();

        for (Rule rule : rr.findAllActiveRules()) {
            for (Query q : rule.getQueries()) {
                activeQueries.add(q.getName());
            }
        }

        List<Query> result = new ArrayList<Query>();

        for (Query query : queries) {
            if (!activeQueries.contains(query.getName())) {
                result.add(query);
            }
        }
        return result;
    }

}
