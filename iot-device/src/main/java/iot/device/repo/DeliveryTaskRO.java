package iot.device.repo;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import common.data.model.DataSource;
import iot.device.property.Configuration;

public class DeliveryTaskRO {

    private URL urlDataSink;

    private Configuration configuration = new Configuration();

    public Set<DataSource> dataSources = new HashSet<DataSource>();

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

    public Set<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Set<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public boolean notUsed() {

        if (CollectionUtils.isEmpty(dataSources)) {
            return true;
        }

        return false;
    }
}
