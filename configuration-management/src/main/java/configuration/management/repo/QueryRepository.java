package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.Query;

public interface QueryRepository extends CrudRepository<Query, Long> {

    public Query findByName(@Param("name") String name);

}
