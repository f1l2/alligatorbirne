package iot.device.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DeliveryTaskRepository {

    private Map<Long, DeliveryTaskRO> repo = new LinkedHashMap<Long, DeliveryTaskRO>();

    public DeliveryTaskRO findOne(Long id) {

        if (repo.containsKey(id)) {
            return repo.get(id);
        }

        return null;
    }

    public List<DeliveryTaskRO> findAll() {

        List<DeliveryTaskRO> jobs = new ArrayList<DeliveryTaskRO>();

        Iterator<DeliveryTaskRO> it = repo.values().iterator();
        while (it.hasNext()) {
            jobs.add(it.next());
        }

        return jobs;
    }

    public void save(DeliveryTaskRO job) {
        repo.put(job.getEventProcessingId(), job);
    }

}
