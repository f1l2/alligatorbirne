package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;

import configuration.management.model.EventProcessingRO;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessingRO, Connection> {

    @Override
    public EventProcessingRO toLocal(Connection remote) {

        EventProcessingRO eProcJPA = new EventProcessingRO();
        eProcJPA.setId(remote.getId());
        eProcJPA.setUrl(remote.getUrl());

        return eProcJPA;
    }

    @Override
    public Connection toRemote(EventProcessingRO local) {

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
