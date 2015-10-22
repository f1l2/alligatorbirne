package common.data;

import java.util.ArrayList;
import java.util.List;

public class DataSources {

    private List<DataSource> dataSources = new ArrayList<DataSource>();

    public DataSources() {

    }

    public DataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public void add(DataSource dataSource) {
        this.dataSources.add(dataSource);
    }

    public void add(List<DataSource> dataSources) {
        for (DataSource measurementPoint : dataSources) {
            add(measurementPoint);
        }
    }

    public List<DataSource> getDataSources() {
        return this.dataSources;
    }

}
