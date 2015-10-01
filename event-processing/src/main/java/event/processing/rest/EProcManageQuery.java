package event.processing.rest;

import java.util.List;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface EProcManageQuery {

    public void register(String query);

    public void unregister(String query);

    public void registerRule(String rule);

    public void unregisterRule(String rule);

    public List<Query> getAllQueries();

    public List<Rule> getAllRules();

}
