package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.IoTDeviceDataSourceRO;


public interface IoTDeviceDataSourceRepository extends CrudRepository<IoTDeviceDataSourceRO, Long> {

	public List<IoTDeviceDataSourceRO> findByDomain(@Param("domain") String domain);
	
	public List<IoTDeviceDataSourceRO> findByDeviceInformation(@Param("deviceInformation") String deviceInformation);
	
	public List<IoTDeviceDataSourceRO> findByDomainAndDeviceInformation(@Param("domain") String domain, @Param("deviceInformation") String deviceInformation);
}
