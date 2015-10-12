package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.EventProcessingRO;
import configuration.management.repo.EventProcessingRepository;

@Component
public class HeartbeatEP extends Activity<Connection> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatEP.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        EventProcessingRO item = eventProcessingRepo.findByAuthority(connection.getUrl().getAuthority());
        if (item != null) {
            item.setUpdated(new Date());
            eventProcessingRepo.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due EP is not registered.");
            this.setErrorResponse(new ResponseEntity<Connection>(connection, HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return next(connection);
    }
}
