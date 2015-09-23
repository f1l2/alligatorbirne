package at.prototype.iot.device;

import org.junit.Assert;
import org.junit.Test;

import common.data.Connection;
import common.data.config.UtilsConfiguration;

public class TestConfigUtils {

    @Test
    public void testLoadCMConnection() throws Exception {

        Connection connection = UtilsConfiguration.getCMConnection();

        Assert.assertNotNull(connection);
        Assert.assertEquals("127.0.0.1", connection.getUrl().getHost());
        Assert.assertEquals("5000", connection.getUrl().getPort());
        Assert.assertEquals("127.0.0.1:5000", connection.getUrl().getAuthority());
    }

    @Test
    public void testLoadIoTDevicesConnection() throws Exception {

        Connection connection = UtilsConfiguration.getIoTDeviceConnection();

        Assert.assertNotNull(connection);
        Assert.assertEquals("127.0.0.1", connection.getUrl().getHost());
        Assert.assertEquals("5002", connection.getUrl().getPort());
        Assert.assertEquals("127.0.0.1:5002", connection.getUrl().getAuthority());

    }

}
