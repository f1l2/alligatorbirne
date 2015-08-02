package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.DeviceJPA;

public interface DeviceRepository extends CrudRepository<DeviceJPA, Long> {

		public DeviceJPA findByName(@Param("name") String name);
}
