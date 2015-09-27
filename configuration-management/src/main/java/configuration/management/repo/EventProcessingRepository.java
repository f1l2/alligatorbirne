package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingRO;

public interface EventProcessingRepository extends CrudRepository<EventProcessingRO, Long> {

    public EventProcessingRO findByName(@Param("name") String name);

    public EventProcessingRO findByAuthority(@Param("authority") String authority);

    public List<EventProcessingRO> findByUpdatedBefore(Date date);

}
