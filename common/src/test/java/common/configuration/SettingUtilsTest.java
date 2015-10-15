package common.configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import common.data.Connection;
import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.Setting;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;

public class SettingUtilsTest {

    private static final String PATH_TO_SETTING_FILE = "src/test/resources/setting.xml";

    private static final String PATH_TO_TEST_OUTPUT = "target/setting_test_output.xml";

    private File settingFile;

    private URI settingURI;

    private Setting setting;

    @Before
    public void before() throws MalformedURLException, JAXBException, SAXException {
        settingFile = new File(PATH_TO_SETTING_FILE);
        settingURI = settingFile.toURI();
        settingURI = settingURI.normalize();

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

        Assert.assertEquals("SENSOR 1", newSetting.getDataSources().get(1).getDeviceInformation().getName());
        Assert.assertEquals("DOMAIN 1", newSetting.getDataSources().get(1).getDomainInformation().getName());

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

}