package configuration.management.rest.activity.receive;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import configuration.management.model.DeviceDLO;

@Component
public class HeartbeatDevice extends ReceiveActivity<String, Long> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatDevice.class);

    @Override
    public ResponseEntity<String> doStep(Long id) {

        DeviceDLO item = deviceRepository.findOne(id);

        if (item != null) {
            item.setUpdated(new Date());
            deviceRepository.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due IoTDevice is not registered.");
            this.setErrorResponse(new ResponseEntity<String>("Heartbeat couldn't be processed due IoTDevice is not registered.", HttpStatus.BAD_REQUEST));
        }
        return next("OK", id);
    }
}
