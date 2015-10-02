package event.processing.engine;

import java.io.IOException;
import java.util.List;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface LanguageTransformer {

    public List<String> transformQuery(String query) throws IOException;

    public List<String> transformQuery(Query query);

    public List<String> transformRule(String rule) throws IOException;

    public List<String> transformRule(Rule rule) throws IOException;

}
