package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import configuration.management.model.IoTDeviceRO;

public interface IoTDeviceRepository extends CrudRepository<IoTDeviceRO, Long> {

    public IoTDeviceRO findByName(@Param("name") String name);

    public IoTDeviceRO findByAuthority(@Param("authority") String authority);

    public List<IoTDeviceRO> findByUpdatedBefore(Date date);

    @Query("select i from IoTDeviceRO as i left join i.ioTDeviceDataSources as ds where ds.device = ?1 and ds.domain = ?2")
    public List<IoTDeviceRO> findByIoTDeviceDataSources(String device, String domain);

}
