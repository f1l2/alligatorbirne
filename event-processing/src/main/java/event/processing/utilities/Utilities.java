package event.processing.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.messaging.MessageHandler;
import event.processing.query.Query;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;
import event.processing.rule.model.Reaction;

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

    public static String createSelectorForActiveRules(RuleRepository rr) {

        StringBuilder sb = new StringBuilder();

        Set<String> selectors = new HashSet<String>();

        for (Rule rule : rr.findAllActiveRules()) {

            if (rule.getIsActivated()) {

                for (Reaction reaction : rule.getReactions()) {
                    selectors.add(getSelectorByReaction(reaction));
                }
            }
        }

        if (selectors.size() > 0) {
            for (String reaction : selectors) {
                sb.append(" OR ");
                sb.append(reaction);
            }
            return sb.toString().replaceFirst(" OR ", "");
        } else {
            return null;
        }
    }

    public static String getSelectorByReaction(Reaction reaction) {

        StringBuilder sb = new StringBuilder();
        sb.append(MessageHandler.PROPERTY);
        sb.append(" = ");
        sb.append("'");
        sb.append(reaction.getDeviceInformation());
        sb.append(":");
        sb.append(reaction.getDomainInformation());
        sb.append("'");

        return sb.toString();

    }

}
