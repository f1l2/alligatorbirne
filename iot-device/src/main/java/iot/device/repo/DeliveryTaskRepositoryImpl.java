package iot.device.repo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DeliveryTaskRepositoryImpl implements DeliveryTaskRepository {

    private Map<String, DeliveryTaskRO> repo = new LinkedHashMap<String, DeliveryTaskRO>();

    @Override
    public DeliveryTaskRO findByAuthority(String authority) {

        if (repo.containsKey(authority)) {
            return repo.get(authority);
        }
        return null;
    }

    @Override
    public DeliveryTaskRO findByUrl(URL url) {

        if ((null != url) && (null != url.getAuthority())) {
            return findByAuthority(url.getAuthority());
        }
        return new DeliveryTaskRO();
    }

    @Override
    public List<DeliveryTaskRO> findAll() {

        List<DeliveryTaskRO> tasks = new ArrayList<DeliveryTaskRO>();

        Iterator<DeliveryTaskRO> it = repo.values().iterator();
        while (it.hasNext()) {
            tasks.add(it.next());
        }
        return tasks;
    }

    @Override
    public void save(DeliveryTaskRO task) {
        repo.put(task.getUrlDataSink().getAuthority(), task);
    }

    @Override
    public void delete(DeliveryTaskRO task) {
        if (repo.containsKey(task.getUrlDataSink().getAuthority())) {
            repo.remove(task.getUrlDataSink().getAuthority());
        }
    }
}
