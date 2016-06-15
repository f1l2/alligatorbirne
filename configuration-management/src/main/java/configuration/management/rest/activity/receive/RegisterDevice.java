package configuration.management.rest.activity.receive;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.Connection;
import configuration.management.model.DeviceDLO;

@Component
public class RegisterDevice extends ReceiveActivity<Connection, Connection> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDevice.class);

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {

        /**
         * If device with URL already exists, return existing values. Otherwise generate new values.
         */

        DeviceDLO item = deviceRepository.findByAuthority(connection.getUrl().getAuthority());
        if (null != item) {
            connection.setId(item.getId());
            item.setUpdated(new Date());
        } else {
            item = new DeviceDLO();
            item.setCreated(new Date());
            item.setUpdated(new Date());
            item.setAuthority(connection.getUrl().getAuthority());
        }

        item = deviceRepository.save(item);
        connection.setId(item.getId());

        return next(connection, connection);
    }

}
