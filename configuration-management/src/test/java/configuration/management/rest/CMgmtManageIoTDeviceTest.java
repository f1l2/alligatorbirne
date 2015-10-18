package configuration.management.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.response.Response;

import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.type.COMPONENT_TYPE;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsUrl;
import configuration.management.AbstractTestRestCM;
import configuration.management.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CMgmtManageIoTDeviceTest extends AbstractTestRestCM {

    @Test
    public void getAll1() {

        when().get(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES.getPath())

        .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @Transactional
    public void getAll2() {

        register(null);

        Response response = get(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES.getPath());

        List<Connection> result = Arrays.asList(response.getBody().as(Connection[].class));

        assertEquals(200, response.getStatusCode());
        assertNotNull(result);
        assertNotEquals(0, result.size());
        assertNotNull(result.get(0).getId());
    }

    @Test
    @Transactional
    public void registerDataSources() {

        URL url = UtilsUrl.parseUrl("localhost:5003");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
        connection.setName("DEV_NAME3");
        connection.setUrl(url);

        Connection register = register(connection);

        DeviceInformation dev = new DeviceInformation();
        dev.setName("device");
        dev.setType(DEVICE_INFORMATION_TYPE.SENSOR);
        DomainInformation domain = new DomainInformation();
        domain.setName("domain");
        domain.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(dev);
        dataSource.setDomainInformation(domain);

        DataSources dataSources = new DataSources();
        dataSources.add(dataSource);
        dataSources.add(dataSource);

        String path = RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES.getPath();
        path = path.replace("{id}", Long.toString(register.getId()));

        Response response = given().body(dataSources).contentType("application/json").post(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeat() {

        URL url = UtilsUrl.parseUrl("localhost:5005");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
        connection.setName("EP_NAME5");
        connection.setUrl(url);

        // first register device
        connection = register(connection);

        String path = RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE.getPath();
        path = path.replace("{id}", Long.toString(connection.getId()));

        // send heart beat
        Response response = given()
                //
                .when().put(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeatFailMissingRegistration() {

        URL url = UtilsUrl.parseUrl("localhost:5004");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
        connection.setName("EP_NAME4");
        connection.setUrl(url);

        // send heart beat

        String path = RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE.getPath();
        path = path.replace("{id}", "1234");

        Response response = given()
                //
                .when().put(path);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());

    }

    @Test
    @Transactional
    public void getDataSources() {

        URL url = UtilsUrl.parseUrl("localhost:5005");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
        connection.setName("DEV_NAME5");
        connection.setUrl(url);

        Connection register = register(connection);

        DeviceInformation dev = new DeviceInformation();
        dev.setName("device");
        dev.setType(DEVICE_INFORMATION_TYPE.SENSOR);
        DomainInformation domain = new DomainInformation();
        domain.setName("domain");
        domain.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(dev);
        dataSource.setDomainInformation(domain);

        DataSources dataSources = new DataSources();
        dataSources.add(dataSource);
        dataSources.add(dataSource);

        String path = RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES.getPath();
        path = path.replace("{id}", Long.toString(register.getId()));

        Response response = given().body(dataSources).contentType("application/json").post(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // ask for data sources

        path = RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES.getPath();
        path = path.replace("{id}", Long.toString(register.getId()));

        response = given().get(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<DataSource> ds = Arrays.asList(response.getBody().as(DataSource[].class));

        assertNotNull(ds);

        assertEquals(2, ds.size());
        assertEquals(dataSource.getDeviceInformation().getName(), ds.get(0).getDeviceInformation().getName());
        assertEquals(dataSource.getDomainInformation().getName(), ds.get(0).getDomainInformation().getName());
    }

    private Connection register(Connection connection) {

        if (null == connection) {
            URL url = UtilsUrl.parseUrl("localhost:4999");

            connection = new Connection();
            connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
            connection.setName("DEV_NAME");
            connection.setUrl(url);
        }

        Response response = given().body(connection).contentType("application/json")
                //
                .when().post(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE.getPath());

        Connection result = response.getBody().as(Connection.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(COMPONENT_TYPE.IOT_DEVICE, result.getComponentType());
        assertNotNull(result.getUrl());
        assertEquals(result.getUrl().getAuthority(), connection.getUrl().getAuthority());

        return result;
    }
}
