package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.EventProcessingJPA;

public interface EventProcessingRepository extends CrudRepository<EventProcessingJPA, Long> {

    public EventProcessingJPA findByName(@Param("name") String name);
}
