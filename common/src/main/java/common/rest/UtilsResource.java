package common.rest;

import common.data.Connection;

public class UtilsResource {

    public static String getUrl(RESOURCE_NAMING resourceNaming, String ip, String port) {
        return String.format("http://%s:%s%s", ip, port, resourceNaming.getPath());
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, Connection connection) {
        return String.format("http://%s%s", connection.getUrl(), resourceNaming.getPath());
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, String connection) {
        return String.format("http://%s%s", connection, resourceNaming.getPath());
    }

    public static String getLogMessage(RESOURCE_NAMING resourceNaming) {
        return String.format("http://%s %s is invoked.", resourceNaming.getRequestMethod(), resourceNaming.getPath());
    }

}
