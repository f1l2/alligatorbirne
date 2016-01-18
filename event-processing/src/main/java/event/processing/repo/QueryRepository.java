package event.processing.repo;

import java.util.List;

import common.lang.query.QueryLang;

public interface QueryRepository {

    public QueryLang findOne(String query);

    public List<QueryLang> findAllByQueries(List<QueryLang> queries);

    public List<QueryLang> findAll();

    public void save(QueryLang query);

    public void delete(String name);

    public void reset();
}
