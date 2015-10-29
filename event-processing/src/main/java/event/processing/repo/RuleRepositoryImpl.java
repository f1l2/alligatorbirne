package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import common.utilities.NormalizeString;
import event.processing.query.Query;
import event.processing.rule.Rule;

@Component
public class RuleRepositoryImpl implements RuleRepository {

    private Map<String, Rule> repo = new LinkedHashMap<String, Rule>();

    private String before(String name) {
        return NormalizeString.normalize(name);
    }

    @Override
    public Rule findOne(String name) {

        name = before(name);

        if (repo.containsKey(name)) {
            return repo.get(name);
        }

        return null;
    }

    @Override
    public List<Rule> findAll() {

        List<Rule> rules = new ArrayList<Rule>();

        Iterator<Rule> it = repo.values().iterator();
        while (it.hasNext()) {
            rules.add(it.next());
        }

        return rules;
    }

    @Override
    public void save(String name, Rule rule) {

        name = before(name);

        repo.put(name, rule);
    }

    @Override
    public void delete(String name) {

        name = before(name);

        repo.remove(name);
    }

    @Override
    public List<Rule> findRulesByQueryName(String queryName) {

        queryName = before(queryName);

        List<Rule> rules = new ArrayList<Rule>();

        if (null == queryName) {
            return rules;
        }

        Iterator<Rule> it = repo.values().iterator();
        while (it.hasNext()) {

            Rule rule = it.next();

            for (Query query : rule.getQueries()) {

                if (null == query) {
                    continue;
                }

                if (queryName.equals(query.getName())) {
                    rules.add(rule);
                    continue;
                }
            }
        }

        return rules;
    }

    @Override
    public List<Rule> findAllActiveRules() {

        List<Rule> rules = new ArrayList<Rule>();

        Iterator<Rule> it = repo.values().iterator();
        while (it.hasNext()) {

            Rule rule = it.next();

            if (rule.getIsActivated()) {
                rules.add(rule);
            }
        }

        return rules;
    }

}
