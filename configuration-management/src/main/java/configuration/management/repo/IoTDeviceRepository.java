package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.IoTDeviceRO;

public interface IoTDeviceRepository extends CrudRepository<IoTDeviceRO, Long> {

    public IoTDeviceRO findByName(@Param("name") String name);

    public IoTDeviceRO findByUrl(@Param("url") String url);
}
