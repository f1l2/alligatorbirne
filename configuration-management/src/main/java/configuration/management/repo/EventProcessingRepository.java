package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingRO;

public interface EventProcessingRepository extends CrudRepository<EventProcessingRO, Long> {

    public EventProcessingRO findByName(@Param("name") String name);

    public EventProcessingRO findByUrl(@Param("url") String url);
}
