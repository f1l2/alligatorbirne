package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.ConnectionProperties;
import common.transformer.Transformer;

import configuration.management.model.EventProcessingJPA;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessingJPA, ConnectionProperties> {

    @Override
    public EventProcessingJPA toLocal(ConnectionProperties remote) {

        EventProcessingJPA eProcJPA = new EventProcessingJPA();
        eProcJPA.setId(remote.getId());
        eProcJPA.setUrl(remote.getUrl());

        return eProcJPA;
    }

    @Override
    public ConnectionProperties toRemote(EventProcessingJPA local) {

        ConnectionProperties connection = new ConnectionProperties();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
