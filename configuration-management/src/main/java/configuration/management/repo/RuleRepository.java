package configuration.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.RuleDLO;

public interface RuleRepository extends CrudRepository<RuleDLO, Long> {

    public RuleDLO findByName(@Param("name") String name);

    @Query("select i from RuleDLO as i left join i.queries as qs where qs.name = ?1")
    public List<RuleDLO> findByQuery(String query);

}
