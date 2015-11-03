package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.EventProcessing;
import configuration.management.repo.EventProcessingRepository;

@Component
public class RegisterEP extends Activity<Connection, Connection> {

    final static Logger logger = LoggerFactory.getLogger(RegisterEP.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        EventProcessing item = eventProcessingRepo.findByAuthority(connection.getUrl().getAuthority());
        if (null != item) {
            connection.setId(item.getId());
            item.setUpdated(new Date());
        } else {
            item = new EventProcessing();
            item.setCreated(new Date());
            item.setUpdated(new Date());
            item.setAuthority(connection.getUrl().getAuthority());
            item = eventProcessingRepo.save(item);
        }

        item = eventProcessingRepo.save(item);
        connection.setId(item.getId());

        return next(connection, connection);
    }
}
