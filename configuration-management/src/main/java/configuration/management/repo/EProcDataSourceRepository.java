package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EProcDataSourceJPA;

public interface EProcDataSourceRepository extends CrudRepository<EProcDataSourceJPA, Long> {

    public List<EProcDataSourceJPA> findByDomain(@Param("domain") String domain);

    public List<EProcDataSourceJPA> findByDeviceInformation(@Param("deviceInformation") String deviceInformation);

    public List<EProcDataSourceJPA> findByDomainAndDeviceInformation(@Param("domain") String domain, @Param("deviceInformation") String deviceInformation);

    public List<EProcDataSourceJPA> findByEProcId(@Param("eprocId") Long eprocId);
}
