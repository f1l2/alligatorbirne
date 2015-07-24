package configuration.management.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import configuration.management.model.Device;

@RepositoryRestResource(collectionResourceRel = "device", path = "device")
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

	public Device findByName(@Param("name") String name);
}
