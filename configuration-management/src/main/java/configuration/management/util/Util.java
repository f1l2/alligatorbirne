package configuration.management.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

    final static Logger logger = LoggerFactory.getLogger(Util.class);

    public static final String parseHost(String authority) {

        int indexDoublePoint = authority.indexOf(":");

        String host = null;
        if (indexDoublePoint > -1) {
            host = authority.substring(0, indexDoublePoint);
        }

        return host;

    }

    public static final String parsePort(String authority) {
        int indexDoublePoint = authority.indexOf(":");

        String port = null;
        if (indexDoublePoint > -1) {
            port = authority.substring(indexDoublePoint + 1, authority.length());

        }
        return port;
    }

    public static final URL parseUrl(String authority) {

        URL url = null;
        try {
            url = new URL("http", parseHost(authority), Integer.parseInt(parsePort(authority)), "/");

        } catch (Exception ex) {
            logger.error("Error parsing configuration file (connection element). {}", ex);
        }

        return url;
    }

}
