package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.IoTDeviceRO;

public interface IoTDeviceRepository extends CrudRepository<IoTDeviceRO, Long> {

    public IoTDeviceRO findByName(@Param("name") String name);

    public IoTDeviceRO findByAuthority(@Param("authority") String authority);

    public List<IoTDeviceRO> findByUpdatedBefore(Date date);

    public List<IoTDeviceRO> findByIoTDeviceDataSources(@Param("device") String device, @Param("domain") String domain);

}
