package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import event.processing.rule.Rule;

@Component
public class RuleRepositoryImpl implements RuleRepository {

    private Map<String, Rule> repo = new LinkedHashMap<String, Rule>();

    @Override
    public Rule findOne(String name) {

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
        repo.put(name, rule);
    }

    @Override
    public void delete(String name) {
        repo.remove(name);
    }

}
