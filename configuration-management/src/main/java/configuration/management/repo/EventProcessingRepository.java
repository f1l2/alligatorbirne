package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingDLO;

public interface EventProcessingRepository extends CrudRepository<EventProcessingDLO, Long> {

    public EventProcessingDLO findByName(@Param("name") String name);

    public EventProcessingDLO findByAuthority(@Param("authority") String authority);

    public List<EventProcessingDLO> findByUpdatedBefore(Date date);

    @Query("select i from EventProcessingDLO as i left join i.dataSources as ds where ds.device = ?1 and ds.domain = ?2")
    public List<EventProcessingDLO> findByDataSources(String device, String domain);

}
