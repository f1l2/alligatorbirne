package common.data.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.rest.UtilsUrl;

public class TestUtilsResource {

    @Test
    public void testGetUrl1() {

        String authority = "localhost:8080";
        String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, authority);

        assertEquals("http://localhost:8080/delegation/{id}", url);
    }

    @Test
    public void testGetUrl2() {

        String authority = "localhost:8080";

        Connection connection = new Connection();
        connection.setUrl(UtilsUrl.parseUrl(authority));

        String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, connection);

        assertEquals("http://localhost:8080/delegation/{id}", url);
    }

}
