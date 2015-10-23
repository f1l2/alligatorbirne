package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import common.utilities.NormalizeString;
import event.processing.query.Query;

@Component
public class QueryRepositoryImpl implements QueryRepository {

    private Map<String, Query> repo = new LinkedHashMap<String, Query>();

    private String before(String name) {
        return NormalizeString.normalize(name);
    }

    @Override
    public Query findOne(String name) {

        name = before(name);

        if (repo.containsKey(name)) {
            return repo.get(name);
        }
        return null;
    }

    @Override
    public List<Query> findAll() {

        List<Query> queries = new ArrayList<Query>();

        Iterator<Query> it = repo.values().iterator();
        while (it.hasNext()) {
            queries.add(it.next());
        }

        return queries;
    }

    @Override
    public void save(String name, Query query) {

        name = before(name);

        repo.put(name, query);
    }

    @Override
    public void delete(String name) {

        name = before(name);

        repo.remove(name);
    }

}
