package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import common.data.type.COMPONENT_TYPE;
import configuration.management.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    public Device findByName(@Param("name") String name);

    public Device findByAuthority(@Param("authority") String authority);

    public List<Device> findByUpdatedBefore(Date date);

    @Query("select i from Device as i left join i.dataSources as ds where ds.device = ?1 and ds.domain = ?2")
    public List<Device> findByDataSources(String device, String domain);

    @Query("select i from Device as i left join i.dataSources as ds where i.type = ?1 and ds.device = ?2 and ds.domain = ?3")
    public List<Device> findByDataSources(COMPONENT_TYPE type, String device, String domain);

}
