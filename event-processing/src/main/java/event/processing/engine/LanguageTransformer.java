package event.processing.engine;

import java.io.IOException;
import java.util.List;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface LanguageTransformer {

    public List<String> transformQueryString(String in, String name) throws IOException;

    public List<String> transformQueryString(List<String> queries, List<String> names) throws IOException;

    public List<String> transformQuery(Query query);

    public List<String> transformQuery(List<Query> queries);

    public List<String> transformRuleString(String rule) throws IOException;

    public List<String> transformRule(Rule rule) throws IOException;

}
