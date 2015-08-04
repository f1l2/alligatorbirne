package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;

import configuration.management.model.EventProcessingJPA;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessingJPA, Connection> {

    @Override
    public EventProcessingJPA toLocal(Connection remote) {

        EventProcessingJPA eProcJPA = new EventProcessingJPA();
        eProcJPA.setId(remote.getId());
        eProcJPA.setUrl(remote.getUrl());

        return eProcJPA;
    }

    @Override
    public Connection toRemote(EventProcessingJPA local) {

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
