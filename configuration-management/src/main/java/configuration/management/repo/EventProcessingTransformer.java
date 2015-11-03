package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;
import configuration.management.model.EventProcessing;
import configuration.management.util.Util;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessing, Connection> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public EventProcessing toLocal(Connection remote) {

        EventProcessing eProcJPA = new EventProcessing();
        eProcJPA.setId(remote.getId());
        eProcJPA.setAuthority(remote.getUrl().getAuthority());

        return eProcJPA;
    }

    @Override
    public Connection toRemote(EventProcessing local) {

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(Util.parseUrl(local.getAuthority()));

        return connection;
    }

}
