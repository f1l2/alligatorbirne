package event.processing.repo;

import java.util.List;

import common.lang.rule.RuleLang;

public interface RuleRepository {

    public RuleLang findOne(String name);

    public List<RuleLang> findAll();

    public void save(String name, RuleLang rule);

    public void delete(String name);

    public List<RuleLang> findRulesByQueryName(String queryName);

    public List<RuleLang> findAllActiveRules();

    public void reset();

}
