package event.processing.repo;

import java.util.List;

import event.processing.rule.Rule;

public interface RuleRepository {

    public Rule findOne(String name);

    public List<Rule> findAll();

    public void save(String name, Rule rule);

    public void delete(String name);

    public List<Rule> findRulesByQueryName(String queryName);

    public List<Rule> findAllActiveRules();

    public void reset();

}
