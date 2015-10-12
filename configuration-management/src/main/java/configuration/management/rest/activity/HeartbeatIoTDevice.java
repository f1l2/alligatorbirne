package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceRepository;

@Component
public class HeartbeatIoTDevice extends Activity<Connection> {

    final static Logger logger = LoggerFactory.getLogger(HeartbeatIoTDevice.class);

    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        IoTDeviceRO item = deviceRepo.findByAuthority(connection.getUrl().getAuthority());
        if (item != null) {
            item.setUpdated(new Date());
            deviceRepo.save(item);
        } else {
            logger.error("Heartbeat couldn't be processed due IoTDevice is not registered.");
            this.setErrorResponse(new ResponseEntity<Connection>(connection, HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return next(connection);
    }
}
