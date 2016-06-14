package common.data;

import java.util.List;

import common.data.model.DataSource;

public class Setting {

    private List<Connection> connections;

    private List<DataSource> dataSources;

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }
}
