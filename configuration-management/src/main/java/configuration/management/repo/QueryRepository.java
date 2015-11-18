package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.QueryDLO;

public interface QueryRepository extends CrudRepository<QueryDLO, Long> {

    public QueryDLO findByName(@Param("name") String name);

}
