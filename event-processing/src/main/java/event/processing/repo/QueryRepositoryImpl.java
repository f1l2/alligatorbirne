package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import common.lang.query.QueryLang;
import common.utilities.NormalizeString;

@Component
public class QueryRepositoryImpl implements QueryRepository {

    private Map<String, QueryLang> repo = new LinkedHashMap<String, QueryLang>();

    private String before(String name) {
        return NormalizeString.normalize(name);
    }

    @Override
    public QueryLang findOne(String name) {

        name = before(name);

        if (repo.containsKey(name)) {
            return repo.get(name);
        }
        return null;
    }

    @Override
    public List<QueryLang> findAllByQueries(List<QueryLang> queries) {

        return queries.stream().map(item -> findOne(item.getName())).filter(item -> item != null).collect(Collectors.toList());
    }

    @Override
    public List<QueryLang> findAll() {

        List<QueryLang> queries = new ArrayList<QueryLang>();

        Iterator<QueryLang> it = repo.values().iterator();
        while (it.hasNext()) {
            queries.add(it.next());
        }

        return queries;
    }

    @Override
    public void save(QueryLang query) {

        String name = before(query.getName());

        repo.put(name, query);
    }

    @Override
    public void delete(String name) {

        name = before(name);

        repo.remove(name);
    }

    @Override
    public void reset() {
        repo = new LinkedHashMap<String, QueryLang>();
    }
}
