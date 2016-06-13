package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.rest.UrlUtils;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingDLO;

@Component
public class EventProcessingTransformer extends Transformer<EventProcessingDLO, Connection> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public EventProcessingDLO toLocal(Connection remote) {

        EventProcessingDLO eProcJPA = new EventProcessingDLO();
        eProcJPA.setId(remote.getId());
        eProcJPA.setAuthority(remote.getUrl().getAuthority());

        return eProcJPA;
    }

    @Override
    public Connection toRemote(EventProcessingDLO local) {

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(UrlUtils.parseUrl(local.getAuthority()));
        connection.setName(local.getName());
        connection.setUpdated(local.getUpdated());
        connection.setValue1(local.getCpuUsage());
        connection.setValue2(local.getRamUsage());

        return connection;
    }

}
