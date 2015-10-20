package common.data.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.rest.UrlUtils;

public class UrlUtilsTest {

    @Test
    public void testParse() {
        String authority = "localhost:8080";
        assertEquals("localhost", UrlUtils.parseHost(authority));
        assertEquals("8080", UrlUtils.parsePort(authority));

    }

}
