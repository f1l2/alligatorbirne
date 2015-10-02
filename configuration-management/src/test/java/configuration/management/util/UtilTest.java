package configuration.management.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilTest {

    @Test
    public void testParseHost() {
        String authority = "localhost:8080";
        assertEquals("localhost", Util.parseHost(authority));
        assertEquals("8080", Util.parsePort(authority));

    }

}
