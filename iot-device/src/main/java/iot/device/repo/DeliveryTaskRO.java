package iot.device.repo;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DeliveryTaskRO {

    private URL urlDataSink;

    private Map<String, String> properties = new HashMap<String, String>();

    public URL getUrlDataSink() {
        return urlDataSink;
    }

    public void setUrlDataSink(URL urlDataSink) {
        this.urlDataSink = urlDataSink;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
