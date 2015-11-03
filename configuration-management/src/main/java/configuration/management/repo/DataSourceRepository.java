package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.DataSourceRO;

public interface DataSourceRepository extends CrudRepository<DataSourceRO, Long> {

    public List<DataSourceRO> findByDomain(@Param("domain") String domain);

    public List<DataSourceRO> findByDevice(@Param("device") String device);

    public List<DataSourceRO> findByDomainAndDevice(@Param("domain") String domain, @Param("device") String device);

}
