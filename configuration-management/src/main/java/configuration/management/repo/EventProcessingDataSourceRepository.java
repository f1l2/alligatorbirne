package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingDataSourceRO;

public interface EventProcessingDataSourceRepository extends CrudRepository<EventProcessingDataSourceRO, Long> {

    public List<EventProcessingDataSourceRO> findByDomain(@Param("domain") String domain);

    public List<EventProcessingDataSourceRO> findByDevice(@Param("device") String device);

    public List<EventProcessingDataSourceRO> findByDomainAndDevice(@Param("domain") String domain, @Param("device") String device);

    public List<EventProcessingDataSourceRO> findByEventProcessingId(@Param("eventProcessingId") Long eventProcessingId);
}
