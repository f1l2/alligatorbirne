package configuration.management;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import common.data.DataSource;
import common.data.dto.DataSourcesDTO;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;

public abstract class AbstractTestRestCM {

    @Value("${local.server.port}")
    protected int port;

    protected static int count = 0;

    protected String authority;

    protected DataSourcesDTO dataSources;

    @Before
    public void before() throws IOException {
        RestAssured.port = port;

        authority = getRandomHost();

        dataSources = getDataSources();
    }

    @After
    public void after() {
    }

    private String getRandomHost() {
        return "localhost" + count + ":8080";
    }

    protected DataSourcesDTO getDataSources() {

        DeviceInformation dev = new DeviceInformation();
        dev.setName("device");
        dev.setType(DEVICE_INFORMATION_TYPE.SENSOR);
        DomainInformation domain = new DomainInformation();
        domain.setName("domain");
        domain.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(dev);
        dataSource.setDomainInformation(domain);

        DataSourcesDTO dataSources = new DataSourcesDTO();
        dataSources.add(dataSource);
        dataSources.add(dataSource);

        return dataSources;

    }
}
