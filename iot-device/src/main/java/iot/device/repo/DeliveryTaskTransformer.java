package iot.device.repo;

import org.springframework.stereotype.Component;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.transformer.Transformer;

@Component
public class DeliveryTaskTransformer extends Transformer<DeliveryTaskRO, ConfigurationModification> {

    @Override
    public DeliveryTaskRO toLocal(ConfigurationModification remote) {

        DeliveryTaskRO item = new DeliveryTaskRO();
        item.setProperties(remote.getProperties());
        item.setUrlDataSink(remote.getDataSink().getUrl());

        return item;
    }

    @Override
    public ConfigurationModification toRemote(DeliveryTaskRO local) {

        Connection dataSink = new Connection();
        dataSink.setUrl(local.getUrlDataSink());

        ConfigurationModification item = new ConfigurationModification();
        item.setDataSink(dataSink);
        item.setProperties(local.getProperties());

        return item;
    }
}
