package common.data.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;

public class ResourceUtilsTest {

    @Test
    public void testGetUrl1() {

        String authority = "localhost:8080";
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DELEGATION, authority);

        assertEquals("http://localhost:8080/delegation" + "", url);
    }

    @Test
    public void testGetUrl2() {

        String authority = "localhost:8080";

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl(authority));

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DELEGATION, connection);

        assertEquals("http://localhost:8080/delegation", url);
    }

    @Test
    public void testGetUrlWithParameter1() {

        String authority = "localhost:8080";

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl(authority));

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTER_DEVICE_SOURCES, connection, "123");

        assertEquals("http://localhost:8080/registrations/devices/sources/123", url);

    }

    @Test
    public void testGetUrlWithParameter2() {

        String authority = "localhost:8080";

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl(authority));

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_DEVICE_BY_DATA_SOURCES, connection, "dev", "dom");

        assertEquals("http://localhost:8080/registrations/devices/sources/dev/dom", url);

    }

}
