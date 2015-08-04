package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.DeviceDataSourceJPA;


public interface MeasurementPointRepository extends CrudRepository<DeviceDataSourceJPA, Long> {

	public List<DeviceDataSourceJPA> findByDomain(@Param("domain") String domain);
	
	public List<DeviceDataSourceJPA> findByDeviceInformation(@Param("deviceInformation") String deviceInformation);
	
	public List<DeviceDataSourceJPA> findByDomainAndDeviceInformation(@Param("domain") String domain, @Param("deviceInformation") String deviceInformation);
}
