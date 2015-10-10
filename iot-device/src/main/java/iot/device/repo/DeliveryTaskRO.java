package iot.device.repo;

import java.net.URL;
import java.util.Properties;

public class DeliveryTaskRO {

    private URL urlDataSink;

    private Properties properties = new Properties();

    public URL getUrlDataSink() {
        return urlDataSink;
    }

    public void setUrlDataSink(URL urlDataSink) {
        this.urlDataSink = urlDataSink;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
