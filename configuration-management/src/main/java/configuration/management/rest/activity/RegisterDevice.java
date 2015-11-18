package configuration.management.rest.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.DeviceDLO;
import configuration.management.repo.DeviceRepository;

@Component
public class RegisterDevice extends Activity<Connection, Connection> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDevice.class);

    @Autowired
    private DeviceRepository deviceRepo;

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        /**
         * If device with URL already exists, return existing values. Otherwise generate new values.
         */

        DeviceDLO item = deviceRepo.findByAuthority(connection.getUrl().getAuthority());
        if (null != item) {
            connection.setId(item.getId());
            item.setUpdated(new Date());
        } else {
            item = new DeviceDLO();
            item.setCreated(new Date());
            item.setUpdated(new Date());
            item.setAuthority(connection.getUrl().getAuthority());
            item = deviceRepo.save(item);
        }

        item = deviceRepo.save(item);
        connection.setId(item.getId());

        return next(connection, connection);
    }

}
