package event.processing.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;
import common.lang.rule.model.Reaction;
import common.messaging.MessageHandler;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;

public class RepoUtilities {

    public static RuleLang findQueriesToQueryNames(RuleLang r, QueryRepository qr) throws Exception {

        List<QueryLang> queries = new ArrayList<QueryLang>();
        for (String queryName : r.getQueryNames()) {
            QueryLang query = qr.findOne(queryName);

            if (null != query) {
                queries.add(query);
            } else {
                throw new Exception();
            }
        }

        r.setQueries(queries);

        return r;
    }

    public static List<QueryLang> filterActiveQueries(List<QueryLang> queries, RuleRepository rr) {

        Set<String> activeQueries = new HashSet<String>();

        for (RuleLang rule : rr.findAllActiveRules()) {
            for (QueryLang q : rule.getQueries()) {
                activeQueries.add(q.getName());
            }
        }

        List<QueryLang> result = new ArrayList<QueryLang>();

        for (QueryLang query : queries) {
            if (!activeQueries.contains(query.getName())) {
                result.add(query);
            }
        }
        return result;
    }

    public static String createSelectorForActiveRules(RuleRepository rr) {

        StringBuilder sb = new StringBuilder();

        Set<String> selectors = new HashSet<String>();

        for (RuleLang rule : rr.findAllActiveRules()) {

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
