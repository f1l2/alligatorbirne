package configuration.management.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.rest.UrlUtils;

public class UtilTest {

    @Test
    public void testParseHost() {
        String authority = "localhost:8080";
        assertEquals("localhost", UrlUtils.parseHost(authority));
        assertEquals("8080", UrlUtils.parsePort(authority));
    }

}
