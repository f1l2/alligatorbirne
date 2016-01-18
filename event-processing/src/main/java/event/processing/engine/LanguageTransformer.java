package event.processing.engine;

import java.io.IOException;
import java.util.List;

import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;

public interface LanguageTransformer {

    public List<String> transformQuery(QueryLang query);

    public List<String> transformQuery(List<QueryLang> queries);

    public List<String> transformRule(RuleLang rule) throws IOException;

    public List<String> transformRulePuristic(RuleLang rule) throws IOException;

}
