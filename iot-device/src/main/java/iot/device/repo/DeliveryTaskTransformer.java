package iot.device.repo;

import org.springframework.stereotype.Component;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.transformer.Transformer;
import iot.device.property.Configuration;

@Component
public class DeliveryTaskTransformer extends Transformer<DeliveryTaskRO, ConfigurationModification> {

    @Override
    public DeliveryTaskRO toLocal(ConfigurationModification remote) {

        Configuration configuration = new Configuration();
        configuration.setAndUpdateProperties(remote.getProperties());

        DeliveryTaskRO item = new DeliveryTaskRO();
        item.setConfiguration(configuration);
        item.setUrlDataSink(remote.getDataSink().getUrl());

        return item;
    }

    @Override
    public ConfigurationModification toRemote(DeliveryTaskRO local) {

        Connection dataSink = new Connection();
        dataSink.setUrl(local.getUrlDataSink());

        ConfigurationModification item = new ConfigurationModification();
        item.setDataSink(dataSink);

        if (null != local.getConfiguration()) {
            item.setProperties(local.getConfiguration().getProperties());
        }
        return item;
    }
}
