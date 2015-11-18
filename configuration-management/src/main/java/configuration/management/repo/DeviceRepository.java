package configuration.management.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import common.data.type.COMPONENT_TYPE;
import configuration.management.model.DeviceDLO;

public interface DeviceRepository extends CrudRepository<DeviceDLO, Long> {

    public DeviceDLO findByName(@Param("name") String name);

    public DeviceDLO findByAuthority(@Param("authority") String authority);

    public List<DeviceDLO> findByUpdatedBefore(Date date);

    @Query("select i from DeviceDLO as i left join i.dataSources as ds where ds.device = ?1 and ds.domain = ?2")
    public List<DeviceDLO> findByDataSources(String device, String domain);

    @Query("select i from DeviceDLO as i left join i.dataSources as ds where i.type = ?1 and ds.device = ?2 and ds.domain = ?3")
    public List<DeviceDLO> findByDataSources(COMPONENT_TYPE type, String device, String domain);

}
