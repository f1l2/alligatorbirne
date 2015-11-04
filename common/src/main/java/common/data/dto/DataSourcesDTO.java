package common.data.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.data.DataSource;

public class DataSourcesDTO {

    private Set<DataSource> dataSources = new HashSet<DataSource>();

    public DataSourcesDTO() {

    }

    public DataSourcesDTO(HashSet<DataSource> dataSources) {
        if (null != dataSources) {
            this.dataSources = dataSources;
        }
    }

    public void add(DataSource dataSource) {
        if (null != dataSource) {
            this.dataSources.add(dataSource);
        }
    }

    public void add(Set<DataSource> dataSources) {
        for (DataSource dataSource : dataSources) {
            this.add(dataSource);
        }
    }

    public void add(List<DataSource> dataSources) {
        for (DataSource dataSource : dataSources) {
            this.add(dataSource);
        }
    }

    public void remove(DataSource dataSource) {
        if (null != dataSources) {
            dataSources.remove(dataSource);
        }
    }

    public void remove(Set<DataSource> dataSources) {
        for (DataSource dataSource : dataSources) {
            this.remove(dataSource);
        }
    }

    public Set<DataSource> getDataSources() {
        return this.dataSources;
    }

    @Override
    public String toString() {
        return "DataSourcesDTO [dataSources=" + dataSources + "]";
    }

}
