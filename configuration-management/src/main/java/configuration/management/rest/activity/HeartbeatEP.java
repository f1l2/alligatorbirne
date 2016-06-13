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

    private Integer value1;

    private Integer value2;

    @Override
    public ResponseEntity<String> doStep(Long id) {

        EventProcessingDLO item = eventProcessingRepo.findOne(id);

        if (item != null) {
            item.setUpdated(new Date());
            item.setCpuUsage(value1);
            item.setRamUsage(value2);
            eventProcessingRepo.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due EP is not registered.");
            this.setErrorResponse(new ResponseEntity<String>("Heartbeat couldn't be processed due EP is not registered.", HttpStatus.BAD_REQUEST));
        }
        return next("OK", id);
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

}
