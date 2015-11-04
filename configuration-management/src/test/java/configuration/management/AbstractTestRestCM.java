package configuration.management;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

import common.data.builder.DSBuilder;
import common.data.dto.DataSourcesDTO;
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
        RestAssured.defaultParser = Parser.JSON;

        authority = getRandomHost();

        dataSources = getDataSources();
    }

    @After
    public void after() {
    }

    private String getRandomHost() {
        count++;
        return "localhost" + count + ":8080";
    }

    protected DataSourcesDTO getDataSources() {

        DSBuilder builder = new DSBuilder();
        builder.buildDataSource("device" + count, DEVICE_INFORMATION_TYPE.SENSOR, "domain" + count, DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);
        builder.buildDataSource("device1" + count, DEVICE_INFORMATION_TYPE.SENSOR, "domain1" + count, DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        return builder.getResult();

    }
}
