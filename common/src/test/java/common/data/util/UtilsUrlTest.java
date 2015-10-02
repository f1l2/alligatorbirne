package common.data.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.rest.UtilsUrl;

public class UtilsUrlTest {

    @Test
    public void testParse() {
        String authority = "localhost:8080";
        assertEquals("localhost", UtilsUrl.parseHost(authority));
        assertEquals("8080", UtilsUrl.parsePort(authority));

    }

}
