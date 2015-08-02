package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;

import configuration.management.model.MeasuringPointJPA;

public interface MeasurementPointRepository extends CrudRepository<MeasuringPointJPA, Long> {

}
