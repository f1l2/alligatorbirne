package common.data.dto;

import java.util.ArrayList;
import java.util.List;

import common.data.DataSource;

public class DataSourcesDTO {

    private List<DataSource> dataSources = new ArrayList<DataSource>();

    public DataSourcesDTO() {

    }

    public DataSourcesDTO(List<DataSource> dataSources) {
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
