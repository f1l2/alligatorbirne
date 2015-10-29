package event.processing.engine;

import java.io.IOException;
import java.util.List;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface LanguageTransformer {

    public List<String> transformQuery(Query query);

    public List<String> transformQuery(List<Query> queries);

    public List<String> transformRule(Rule rule) throws IOException;

    public List<String> transformRulePuristic(Rule rule) throws IOException;

}
