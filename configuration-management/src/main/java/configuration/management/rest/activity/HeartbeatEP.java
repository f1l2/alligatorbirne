package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.EventProcessingRepository;

@Component
public class HeartbeatEP extends Activity<String, Long> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatEP.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Override
    public ResponseEntity<String> doStep(Long id) {

        EventProcessingDLO item = eventProcessingRepo.findOne(id);

        if (item != null) {
            item.setUpdated(new Date());
            eventProcessingRepo.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due EP is not registered.");
            this.setErrorResponse(new ResponseEntity<String>("Heartbeat couldn't be processed due EP is not registered.", HttpStatus.BAD_REQUEST));
        }
        return next("OK", id);
    }
}
