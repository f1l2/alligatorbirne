package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;

import common.repository.RepositoryCommon;
import configuration.management.model.QueryDLO;

public interface QueryRepository extends CrudRepository<QueryDLO, Long>, RepositoryCommon<QueryDLO> {

}
