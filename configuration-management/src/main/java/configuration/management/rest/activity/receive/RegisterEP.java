package configuration.management.rest.activity.receive;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.EventProcessingDLO;

@Component
public class RegisterEP extends ReceiveActivity<Connection, Connection> {

    final static Logger logger = LoggerFactory.getLogger(RegisterEP.class);

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        EventProcessingDLO item = eventProcessingRepository.findByAuthority(connection.getUrl().getAuthority());
        if (null != item) {
            connection.setId(item.getId());
            item.setUpdated(new Date());
        } else {
            item = new EventProcessingDLO();
            item.setCreated(new Date());
            item.setUpdated(new Date());
            item.setAuthority(connection.getUrl().getAuthority());
            item = eventProcessingRepository.save(item);
        }

        item = eventProcessingRepository.save(item);
        connection.setId(item.getId());

        return next(connection, connection);
    }
}
