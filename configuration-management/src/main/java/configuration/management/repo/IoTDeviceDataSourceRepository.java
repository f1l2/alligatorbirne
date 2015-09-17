package configuration.management.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.IoTDeviceDataSourceRO;

public interface IoTDeviceDataSourceRepository extends CrudRepository<IoTDeviceDataSourceRO, Long> {

    public List<IoTDeviceDataSourceRO> findByDomain(@Param("domain") String domain);

    public List<IoTDeviceDataSourceRO> findByDevice(@Param("device") String device);

    public List<IoTDeviceDataSourceRO> findByDomainAndDevice(@Param("domain") String domain, @Param("device") String device);
}
