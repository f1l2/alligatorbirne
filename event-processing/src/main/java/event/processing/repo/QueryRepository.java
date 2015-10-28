package event.processing.repo;

import java.util.List;

import event.processing.query.Query;

public interface QueryRepository {

    public Query findOne(String query);

    public List<Query> findAllByQueries(List<Query> queries);

    public List<Query> findAll();

    public void save(Query query);

    public void delete(String name);
}
