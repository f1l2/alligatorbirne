package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingRO;
import configuration.management.util.Util;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessingRO, Connection> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public EventProcessingRO toLocal(Connection remote) {

        EventProcessingRO eProcJPA = new EventProcessingRO();
        eProcJPA.setId(remote.getId());
        eProcJPA.setAuthority(remote.getUrl().getAuthority());

        return eProcJPA;
    }

    @Override
    public Connection toRemote(EventProcessingRO local) {

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(Util.parseUrl(local.getAuthority()));

        return connection;
    }

}
