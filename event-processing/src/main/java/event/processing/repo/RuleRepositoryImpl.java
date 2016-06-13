package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;
import common.utilities.NormalizeString;

@Component
public class RuleRepositoryImpl implements RuleRepository {

    private Map<String, RuleLang> repo = new LinkedHashMap<>();

    private String before(String name) {
        return NormalizeString.normalize(name);
    }

    @Override
    public RuleLang findOne(String name) {

        name = before(name);

        if (repo.containsKey(name)) {
            return repo.get(name);
        }

        return null;
    }

    @Override
    public List<RuleLang> findAll() {

        List<RuleLang> rules = new ArrayList<>();

        Iterator<RuleLang> it = repo.values().iterator();
        while (it.hasNext()) {
            rules.add(it.next());
        }

        return rules;
    }

    @Override
    public void save(String name, RuleLang rule) {

        name = before(name);

        repo.put(name, rule);
    }

    @Override
    public void delete(String name) {

        name = before(name);

        repo.remove(name);
    }

    @Override
    public List<RuleLang> findRulesByQueryName(String queryName) {

        queryName = before(queryName);

        List<RuleLang> rules = new ArrayList<RuleLang>();

        if (null == queryName) {
            return rules;
        }

        Iterator<RuleLang> it = repo.values().iterator();
        while (it.hasNext()) {

            RuleLang rule = it.next();

            for (QueryLang query : rule.getQueries()) {

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
    public List<RuleLang> findAllActiveRules() {

        List<RuleLang> rules = new ArrayList<RuleLang>();

        Iterator<RuleLang> it = repo.values().iterator();
        while (it.hasNext()) {

            RuleLang rule = it.next();

            if (rule.getIsActivated()) {
                rules.add(rule);
            }
        }

        return rules;
    }

    @Override
    public void reset() {
        repo = new LinkedHashMap<String, RuleLang>();
    }

}
