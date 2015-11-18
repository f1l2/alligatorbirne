package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;

import configuration.management.model.LogDLO;

public interface LogRepository extends CrudRepository<LogDLO, Long> {

}
