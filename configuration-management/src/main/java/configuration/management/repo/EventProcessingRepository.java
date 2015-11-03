package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessing;

public interface EventProcessingRepository extends CrudRepository<EventProcessing, Long> {

    public EventProcessing findByName(@Param("name") String name);

    public EventProcessing findByAuthority(@Param("authority") String authority);

    public List<EventProcessing> findByUpdatedBefore(Date date);

    @Query("select i from EventProcessing as i left join i.dataSources as ds where ds.device = ?1 and ds.domain = ?2")
    public List<EventProcessing> findByDataSources(String device, String domain);

}
