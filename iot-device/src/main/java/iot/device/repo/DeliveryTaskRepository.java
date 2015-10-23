package iot.device.repo;

import java.net.URL;
import java.util.List;

public interface DeliveryTaskRepository {

    public DeliveryTaskRO findByAuthority(String authority);

    public DeliveryTaskRO findByUrl(URL url);

    public List<DeliveryTaskRO> findAll();

    public void save(DeliveryTaskRO task);

    public Boolean create(DeliveryTaskRO task);

    public void delete(DeliveryTaskRO task);

}
