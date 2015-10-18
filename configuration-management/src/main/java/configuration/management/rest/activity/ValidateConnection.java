package configuration.management.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;

@Component
public class ValidateConnection extends Activity<Connection, Connection> {

    final static Logger logger = LoggerFactory.getLogger(ValidateConnection.class);

    private COMPONENT_TYPE ct;

    @Override
    public ResponseEntity<Connection> doStep(Connection connection) {
        if ((null == connection.getUrl()) || (StringUtils.isEmpty(connection.getUrl().getAuthority()))) {
            logger.error("Registration failed due missing URL authority");
            return new ResponseEntity<Connection>(connection, HttpStatus.BAD_REQUEST);
        }

        if ((null == connection.getComponentType()) || (!connection.getComponentType().equals(ct))) {
            logger.error("Registration failed due wrong component type");
            return new ResponseEntity<Connection>(connection, HttpStatus.BAD_REQUEST);
        }

        return next(connection, connection);
    }

    public void setCt(COMPONENT_TYPE ct) {
        this.ct = ct;
    }

}
