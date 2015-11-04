package common.data;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ConfigurationDelegation {

    private Connection dataSink;

    private Set<DataSource> dataSources = new HashSet<DataSource>();

    private Properties properties = new Properties();

    public Connection getDataSink() {
        return dataSink;
    }

    public void setDataSink(Connection dataSink) {
        this.dataSink = dataSink;
    }

    public Set<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Set<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
