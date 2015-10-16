package iot.device;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.data.Connection;
import common.data.setting.SettingUtils;

public class ConfigUtilsTest {

    @Before
    public void before() {
        SettingUtils.setPATH_TO_SETTING_FILE("src/main/resources/setting/dev-setting.xml");
    }

    @Test
    public void testLoadCMConnection() throws Exception {

        Connection connection = SettingUtils.getCMConnection();

        Assert.assertNotNull(connection);
        Assert.assertEquals("127.0.0.1", connection.getUrl().getHost());
        Assert.assertEquals(5000, connection.getUrl().getPort());
        Assert.assertEquals("127.0.0.1:5000", connection.getUrl().getAuthority());
    }

    @Test
    public void testLoadIoTDevicesConnection() throws Exception {

        Connection connection = SettingUtils.getLocalConnection();

        Assert.assertNotNull(connection);

        /**
         * Values influenced by other rest tests. Search for free port and ignores application properties file.
         */
        // Assert.assertEquals("127.0.0.1", connection.getUrl().getHost());
        // Assert.assertEquals(5003, connection.getUrl().getPort());
        // Assert.assertEquals("127.0.0.1:5002", connection.getUrl().getAuthority());

    }

}
