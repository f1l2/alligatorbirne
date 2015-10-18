package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceRepository;

@Component
public class HeartbeatIoTDevice extends Activity<String, Long> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatIoTDevice.class);

    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Override
    public ResponseEntity<String> doStep(Long id) {

        IoTDeviceRO item = deviceRepo.findOne(id);

        if (item != null) {
            item.setUpdated(new Date());
            deviceRepo.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due IoTDevice is not registered.");
            this.setErrorResponse(new ResponseEntity<String>("Heartbeat couldn't be processed due IoTDevice is not registered.", HttpStatus.BAD_REQUEST));
        }
        return next("OK", id);
    }
}
