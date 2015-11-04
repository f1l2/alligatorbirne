package configuration.management.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;
import configuration.management.AbstractTestRestCM;
import configuration.management.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CMgmtManageDeviceTest extends AbstractTestRestCM {

    @Test
    public void getAll1() {

        when().get(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES.getPath())

        .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @Transactional
    public void getAll2() {

        register();

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

        Connection connection = register();

        Response response = given().body(dataSources).contentType("application/json").post(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, connection.getId()));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeat() {

        // first register device
        Connection connection = register();

        // send heart beat
        Response response = given()
                //
                .when().put(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE, connection.getId()));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeatFailMissingRegistration() {

        register();

        // send heart beat
        Response response = given()
                //
                .when().put(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE, "1234"));

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());

    }

    @Test
    @Transactional
    public void getDataSourcesByDevice() {

        Connection register = register();

        Response response = given().body(dataSources).contentType("application/json").post(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, register.getId()));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // ask for data sources

        response = given().get(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES, register.getId()));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<DataSource> ds = Arrays.asList(response.getBody().as(DataSource[].class));

        assertNotNull(ds);

        assertEquals(2, ds.size());

        DataSource dataSource = new ArrayList<DataSource>(dataSources.getDataSources()).get(0);

        assertEquals(dataSource.getDeviceInformation().getName(), ds.get(0).getDeviceInformation().getName());
        assertEquals(dataSource.getDomainInformation().getName(), ds.get(0).getDomainInformation().getName());
    }

    @Test
    @Transactional
    public void getDevicesByDataSource() {

        Connection register = register();

        Response response = given().body(dataSources).contentType("application/json").post(ResourceUtils.getPath(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, register.getId()));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        // ask for data sources

        String path = ResourceUtils.getPath(RESOURCE_NAMING.CMGGT_GET_DEVICE_BY_DATA_SOURCES, "device" + count, "domain" + count);

        response = given().get(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Connection> connections = Arrays.asList(response.getBody().as(Connection[].class));

        assertNotNull(connections);
        assertEquals(1, connections.size());
        assertEquals(register.getUrl().getAuthority(), connections.get(0).getUrl().getAuthority());
    }

    private Connection register() {

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.DEVICE);
        connection.setName("DEV_NAME");
        connection.setUrl(UrlUtils.parseUrl(authority));

        Response response = given().body(connection).contentType("application/json")
                //
                .when().post(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE.getPath());

        Connection result = response.getBody().as(Connection.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(COMPONENT_TYPE.DEVICE, result.getComponentType());
        assertNotNull(result.getUrl());
        assertEquals(result.getUrl().getAuthority(), connection.getUrl().getAuthority());

        return result;
    }
}
