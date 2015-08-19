package event.processing.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import event.processing.query.Query;

@Component
public class QueryRepository {

    private Map<String, Query> repo = new LinkedHashMap<String, Query>();

    public Query findOne(String query) {

        if (repo.containsKey(query)) {
            return repo.get(query);
        }

        return null;
    }

    public List<Query> findAll() {

        List<Query> queries = new ArrayList<Query>();

        Iterator<Query> it = repo.values().iterator();
        while (it.hasNext()) {
            queries.add(it.next());
        }

        return queries;
    }

    public void save(Query query) {
        repo.put(query.toString(), query);
    }

}
