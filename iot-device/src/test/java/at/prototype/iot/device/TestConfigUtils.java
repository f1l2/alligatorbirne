package at.prototype.iot.device;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import common.data.Connection;
import common.data.config.UtilsConfiguration;

public class TestConfigUtils {

    @Test
    public void testLoadCMConnection() throws Exception {

        Connection connection = UtilsConfiguration.getCMConnection();

        Assert.assertNotNull(connection);
        Assert.assertEquals("127.0.0.1", connection.getHost());
        Assert.assertEquals("5000", connection.getPort());
        Assert.assertEquals("127.0.0.1:5000", connection.getHost() + ":" + connection.getPort());
    }

    @Test
    public void testLoadIoTDevicesConnection() throws Exception {

        List<Connection> connections = UtilsConfiguration.getIoTDevicesConnection();

        Assert.assertNotNull(connections);
        Connection connection = connections.get(0);
        Assert.assertEquals("127.0.0.1", connection.getHost());
        Assert.assertEquals("5002", connection.getPort());
        Assert.assertEquals("127.0.0.1:5002", connection.getHost() + ":" + connection.getPort());

    }

}
