package common.rest;

import common.data.Connection;

public class UtilsResource {

    private static final String HTTP_PROTOCOL = "http";

    public static String getUrl(RESOURCE_NAMING resourceNaming, Connection connection) {
        return String.format(HTTP_PROTOCOL + "://%s%s", connection.getUrl().getAuthority(), resourceNaming.getPath());
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, String authority) {
        return String.format(HTTP_PROTOCOL + "://%s%s", authority, resourceNaming.getPath());
    }

    public static String getLogMessage(RESOURCE_NAMING resourceNaming) {
        return String.format("://%s %s is invoked.", resourceNaming.getRequestMethod(), resourceNaming.getPath());
    }

}
