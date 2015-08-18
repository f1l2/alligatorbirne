package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingDataSourceRO;

public interface EventProcessingDataSourceRepository extends CrudRepository<EventProcessingDataSourceRO, Long> {

    public List<EventProcessingDataSourceRO> findByDomain(@Param("domain") String domain);

    public List<EventProcessingDataSourceRO> findByDeviceInformation(@Param("deviceInformation") String deviceInformation);

    public List<EventProcessingDataSourceRO> findByDomainAndDeviceInformation(@Param("domain") String domain, @Param("deviceInformation") String deviceInformation);

    public List<EventProcessingDataSourceRO> findByEventProcessingId(@Param("eprocId") Long eventProcessingId);
}
