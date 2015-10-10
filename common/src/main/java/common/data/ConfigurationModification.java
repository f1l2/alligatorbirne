package common.data;

import java.util.Properties;

public class ConfigurationModification extends DataModel {

    private Connection dataSink;

    private Properties properties = new Properties();

    public Connection getDataSink() {
        return dataSink;
    }

    public void setDataSink(Connection dataSink) {
        this.dataSink = dataSink;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
