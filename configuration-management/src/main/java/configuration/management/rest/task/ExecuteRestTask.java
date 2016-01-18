package configuration.management.rest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteRestTask<T1 extends Task<?>> implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(ExecuteRestTask.class);

    private T1 item;

    public ExecuteRestTask(T1 items) {
        this.item = items;
    }

    @Override
    public void run() {
        item.doTask();
    }
}
