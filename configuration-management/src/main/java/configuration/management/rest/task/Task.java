package configuration.management.rest.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public abstract class Task<T1> {

    final static Logger logger = LoggerFactory.getLogger(ExecuteRestTask.class);

    protected RestTemplate restTemplate = new RestTemplate();

    private List<T1> items;

    public Task(T1 item) {
        items = new ArrayList<T1>();
        items.add(item);
    }

    public Task(List<T1> items) {
        this.items = items;
    }

    public void doTask() {
        this.items.stream().forEach(item -> doTask(item));
    }

    public abstract void doTask(T1 item);

}
