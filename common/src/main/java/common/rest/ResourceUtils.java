package common.rest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.data.Connection;

public class ResourceUtils {

    private static final String HTTP_PROTOCOL = "http";

    public static String getUrl(RESOURCE_NAMING resourceNaming, Connection connection) {
        return String.format(HTTP_PROTOCOL + "://%s%s", connection.getUrl().getAuthority(), resourceNaming.getPath());
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, Connection connection, String... parameters) {

        String url = String.format(HTTP_PROTOCOL + "://%s%s", connection.getUrl().getAuthority(), resourceNaming.getPath());

        return replacePlaceHolder(url, parameters);
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, Connection connection, Long... parameters) {

        String url = String.format(HTTP_PROTOCOL + "://%s%s", connection.getUrl().getAuthority(), resourceNaming.getPath());

        return replacePlaceHolder(url, parameters);
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, String authority) {
        return String.format(HTTP_PROTOCOL + "://%s%s", authority, resourceNaming.getPath());
    }

    public static String getUrl(RESOURCE_NAMING resourceNaming, String authority, String... parameters) {
        String url = String.format(HTTP_PROTOCOL + "://%s%s", authority, resourceNaming.getPath());

        return replacePlaceHolder(url, parameters);
    }

    public static String getPath(RESOURCE_NAMING resourceNaming) {
        return resourceNaming.getPath();
    }

    public static String getPath(RESOURCE_NAMING resourceNaming, String... parameters) {
        return replacePlaceHolder(resourceNaming.getPath(), parameters);
    }

    public static String getPath(RESOURCE_NAMING resourceNaming, Long... parameters) {
        return replacePlaceHolder(resourceNaming.getPath(), parameters);
    }

    public static String getLogMessage(RESOURCE_NAMING resourceNaming) {
        return String.format("%s %s is invoked.", resourceNaming.getRequestMethod(), resourceNaming.getPath());
    }

    private static String replacePlaceHolder(String url, String... parameters) {

        if (countNumberOfPlaceholders(url) != Arrays.asList(parameters).size()) {
            return url;
        }

        for (String parameter : parameters) {
            url = url.replaceFirst("\\{[^/]+\\}", parameter);
        }
        return url;
    }

    private static String replacePlaceHolder(String url, Long... parameters) {

        if (countNumberOfPlaceholders(url) != Arrays.asList(parameters).size()) {
            return url;
        }

        for (Long parameter : parameters) {
            url = url.replaceFirst("\\{[^/]+\\}", Long.toString(parameter));
        }
        return url;
    }

    private static int countNumberOfPlaceholders(String url) {
        Pattern pattern = Pattern.compile("\\{[^/]+\\}");
        Matcher matcher = pattern.matcher(url);

        int count = 0;
        while (matcher.find())
            count++;

        return count;
    }

}
