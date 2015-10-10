package common.data;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationModification extends DataModel {

    private Connection dataSink;

    private Map<String, String> properties = new HashMap<String, String>();

    public Connection getDataSink() {
        return dataSink;
    }

    public void setDataSink(Connection dataSink) {
        this.dataSink = dataSink;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
