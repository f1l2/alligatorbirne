package iot.device.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JobRepository {

    private Map<Long, JobJPA> repo = new LinkedHashMap<Long, JobJPA>();

    public JobJPA findOne(Long id) {

        if (repo.containsKey(id)) {
            return repo.get(id);
        }

        return null;
    }

    public List<JobJPA> findAll() {

        List<JobJPA> jobs = new ArrayList<JobJPA>();

        Iterator<JobJPA> it = repo.values().iterator();
        while (it.hasNext()) {
            jobs.add(it.next());
        }

        return jobs;
    }

    public void save(JobJPA job) {
        repo.put(job.getEpId(), job);
    }

}
