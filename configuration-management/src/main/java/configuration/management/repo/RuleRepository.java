package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Long> {

    public Rule findByName(@Param("name") String name);

}
