package configuration.management.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.data.Connection;

public abstract class DelegationTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DelegationTask.class);

    private List<Connection> connections;

    public DelegationTask(List<Connection> connections) {
        this.connections = connections;
    }

    public abstract void doRun(Connection connection);

    @Override
    public void run() {

        for (Connection connection : connections) {
            doRun(connection);
        }

    }
}
