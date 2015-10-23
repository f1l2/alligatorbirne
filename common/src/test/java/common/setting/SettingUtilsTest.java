package common.setting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import common.data.Connection;
import common.data.DataSource;
import common.data.Setting;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;

public class SettingUtilsTest {

    private static final String PATH_TO_SETTING_FILE = "src/test/resources/setting.xml";

    private static final String PATH_TO_TEST_OUTPUT = "target/setting_test_output.xml";

    private Setting setting;

    @Before
    public void before() throws MalformedURLException, JAXBException, SAXException {

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_SETTING_FILE);

        setting = SettingUtils.loadSetting();

        Assert.assertNotNull(setting);
        Assert.assertNotNull(setting.getConnections());
        Assert.assertNotNull(setting.getDataSources());
        Assert.assertEquals(2, setting.getConnections().size());
        Assert.assertEquals(1, setting.getDataSources().size());
    }

    @Test
    public void saveNewConnection() throws MalformedURLException, JAXBException, SAXException {

        URL url = new URL("http", "localhost", 1234, "/");
        Connection newConnection = new Connection();
        newConnection.setName("new connection");
        newConnection.setUrl(url);

        setting.getConnections().add(newConnection);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        Setting newSetting = SettingUtils.loadSetting();

        Assert.assertNotNull(newSetting);
        Assert.assertNotNull(newSetting.getConnections());
        Assert.assertNotNull(newSetting.getDataSources());
        Assert.assertEquals(3, newSetting.getConnections().size());
        Assert.assertEquals(1, newSetting.getDataSources().size());

        Assert.assertEquals("localhost", newSetting.getConnections().get(2).getUrl().getHost());
        Assert.assertEquals(1234, newSetting.getConnections().get(2).getUrl().getPort());
    }

    @Test
    public void saveNewDataSource() throws MalformedURLException, JAXBException, SAXException {

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName("SENSOR 1");
        deviceInformation.setType(DEVICE_INFORMATION_TYPE.SENSOR);

        DomainInformation domainInformation = new DomainInformation();
        domainInformation.setName("DOMAIN 1");
        domainInformation.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(deviceInformation);
        dataSource.setDomainInformation(domainInformation);

        setting.getDataSources().add(dataSource);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        Setting newSetting = SettingUtils.loadSetting();

        Assert.assertNotNull(newSetting);
        Assert.assertNotNull(newSetting.getConnections());
        Assert.assertNotNull(newSetting.getDataSources());
        Assert.assertEquals(2, newSetting.getConnections().size());
        Assert.assertEquals(2, newSetting.getDataSources().size());

        Assert.assertEquals("sensor 1", newSetting.getDataSources().get(1).getDeviceInformation().getName());
        Assert.assertEquals("domain 1", newSetting.getDataSources().get(1).getDomainInformation().getName());

    }

    @Test
    public void replace() throws MalformedURLException, JAXBException, SAXException {

        URL url = new URL("http", "localhost", 1234, "/");
        Connection newConnection = new Connection();
        newConnection.setName("new connection");
        newConnection.setUrl(url);
        newConnection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);

        Setting newSetting = SettingUtils.replaceConnection(newConnection, COMPONENT_TYPE.IOT_DEVICE);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(newSetting);

        Setting newSetting2 = SettingUtils.loadSetting();

        Assert.assertNotNull(newSetting2);
        Assert.assertNotNull(newSetting2.getConnections());
        Assert.assertNotNull(newSetting2.getDataSources());
        Assert.assertEquals(2, newSetting2.getConnections().size());
        Assert.assertEquals(1, newSetting2.getDataSources().size());

        before();

    }

    @Test
    public void loadDataSourcesByDeviceInformation() throws JAXBException, SAXException, MalformedURLException {

        String name = "blablablabla";

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName(name);
        deviceInformation.setType(DEVICE_INFORMATION_TYPE.SENSOR);

        DomainInformation domainInformation = new DomainInformation();
        domainInformation.setName("DOMAIN 1");
        domainInformation.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(deviceInformation);
        dataSource.setDomainInformation(domainInformation);

        setting.getDataSources().add(dataSource);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        List<DataSource> result = SettingUtils.loadDataSourcesByDeviceInformation(name);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        /**
         * Add arbitrary data source
         */

        DeviceInformation deviceInformation2 = new DeviceInformation();
        deviceInformation2.setName("fadfafds");

        DataSource dataSource2 = new DataSource();
        dataSource2.setDeviceInformation(deviceInformation2);
        dataSource2.setDomainInformation(domainInformation);

        setting.getDataSources().add(dataSource2);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        result = SettingUtils.loadDataSourcesByDeviceInformation(name);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        /**
         * Add data source with specific name
         */

        DeviceInformation deviceInformation3 = new DeviceInformation();
        deviceInformation3.setName(name);
        dataSource.setDeviceInformation(deviceInformation3);

        DataSource dataSource3 = new DataSource();
        dataSource3.setDeviceInformation(deviceInformation3);
        dataSource3.setDomainInformation(domainInformation);

        setting.getDataSources().add(dataSource3);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        result = SettingUtils.loadDataSourcesByDeviceInformation(name);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());

    }

    @Test
    public void loadDomainsByDeviceInformation() throws JAXBException, SAXException, MalformedURLException {

        String name = "blablablablablabla";

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName(name);
        deviceInformation.setType(DEVICE_INFORMATION_TYPE.SENSOR);

        DomainInformation domainInformation = new DomainInformation();
        domainInformation.setName("DOMAIN 1");
        domainInformation.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(deviceInformation);
        dataSource.setDomainInformation(domainInformation);

        setting.getDataSources().add(dataSource);

        SettingUtils.setPATH_TO_SETTING_FILE(PATH_TO_TEST_OUTPUT);

        SettingUtils.saveSetting(setting);

        List<DomainInformation> result = SettingUtils.loadDomainsByDeviceInformation(name);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("domain 1", result.get(0).getName());

    }

}
