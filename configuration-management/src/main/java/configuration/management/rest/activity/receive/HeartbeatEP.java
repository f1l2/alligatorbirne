package configuration.management.rest.activity.receive;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import configuration.management.model.EventProcessingDLO;

@Component
public class HeartbeatEP extends ReceiveActivity<String, Long> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatEP.class);

    private Integer value1;

    private Integer value2;

    @Override
    public ResponseEntity<String> doStep(Long id) {

        EventProcessingDLO item = eventProcessingRepository.findOne(id);

        if (item != null) {
            item.setUpdated(new Date());
            item.setCpuUsage(value1);
            item.setRamUsage(value2);
            eventProcessingRepository.save(item);
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
