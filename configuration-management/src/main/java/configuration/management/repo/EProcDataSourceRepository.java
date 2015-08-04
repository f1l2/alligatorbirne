package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingDataSourceJPA;

public interface EProcDataSourceRepository extends CrudRepository<EventProcessingDataSourceJPA, Long> {

    public List<EventProcessingDataSourceJPA> findByDomain(@Param("domain") String domain);

    public List<EventProcessingDataSourceJPA> findByDeviceInformation(@Param("deviceInformation") String deviceInformation);

    public List<EventProcessingDataSourceJPA> findByDomainAndDeviceInformation(@Param("domain") String domain, @Param("deviceInformation") String deviceInformation);

    public List<EventProcessingDataSourceJPA> findByEProcId(@Param("eprocId") Long eprocId);
}
