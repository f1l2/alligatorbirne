package iot.device.repo;

import java.net.URL;

import iot.device.property.Configuration;

public class DeliveryTaskRO {

    private URL urlDataSink;

    private Configuration configuration = new Configuration();

    public URL getUrlDataSink() {
        return urlDataSink;
    }

    public void setUrlDataSink(URL urlDataSink) {
        this.urlDataSink = urlDataSink;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
