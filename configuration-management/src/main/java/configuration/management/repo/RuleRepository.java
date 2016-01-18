package configuration.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import common.repository.RepositoryCommon;
import configuration.management.model.RuleDLO;

public interface RuleRepository extends CrudRepository<RuleDLO, Long>, RepositoryCommon<RuleDLO> {

    @Query("select i from RuleDLO as i left join i.queries as qs where qs.name = ?1")
    public List<RuleDLO> findByQuery(String query);

}
