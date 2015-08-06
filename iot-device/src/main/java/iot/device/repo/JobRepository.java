package iot.device.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JobRepository {

    private Map<Long, InstructionJPA> repo = new LinkedHashMap<Long, InstructionJPA>();

    public InstructionJPA findOne(Long id) {

        if (repo.containsKey(id)) {
            return repo.get(id);
        }

        return null;
    }

    public List<InstructionJPA> findAll() {

        List<InstructionJPA> jobs = new ArrayList<InstructionJPA>();

        Iterator<InstructionJPA> it = repo.values().iterator();
        while (it.hasNext()) {
            jobs.add(it.next());
        }

        return jobs;
    }

    public void save(InstructionJPA job) {
        repo.put(job.getEpId(), job);
    }

}
