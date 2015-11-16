package monitoring.webapp.ui.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

public final class Messages {

    public enum MESSAGE {
        DISCARD("monitoring.webapp.ui.discard", "Discard"),
        //
        SAVE("monitoring.webapp.ui.save", "Save"),
        //
        CANCEL("monitoring.webapp.ui.cancel", "Cancel"),
        //
        CODE("monitoring.webapp.ui.code", "Code"),
        //
        MESSAGE("monitoring.webapp.ui.message", "Message"),
        //
        NAME("monitoring.webapp.ui.name", "Name"),
        //
        MEP("monitoring.webapp.ui.mep", "MEP"),
        //
        DESCRIPTION("monitoring.webapp.ui.description", "Description"),
        //
        ADD("monitoring.webapp.ui.add", "Add"),
        //
        RESET("monitoring.webapp.ui.reset", "Reset"),
        //
        APPLY("monitoring.webapp.ui.apply", "Apply"),
        //
        UPDATE("monitoring.webapp.ui.update", "Update"),
        //
        REFRESH("monitoring.webapp.ui.refresh", "Refresh"),
        //
        KEYWORD("monitoring.webapp.ui.keyword", "Keyword"),
        //
        SHOW_AT_LEAST("monitoring.webapp.ui.showAtLeast", "Show at least"),
        //
        monitoring_NODE("monitoring.webapp.ui.monitoringNode", "monitoring Node"),
        //
        RELEVANCE("monitoring.webapp.ui.relevance", "Relevance"),
        //
        DEPLOY("monitoring.webapp.ui.deploy", "Deploy"),
        //
        SERVICE_TYPE("monitoring.webapp.ui.serviceType", "Service Type"),
        //
        LABELS("monitoring.webapp.ui.labels", "Labels"),
        //
        OK("monitoring.webapp.ui.ok", "OK"),
        //
        ENVIRONMENT("monitoring.webapp.ui.environment", "Environment"),
        //
        FROM("monitoring.webapp.ui.environment", "From"),
        //
        SERVICE("monitoring.webapp.ui.service", "Service"),
        //
        SERVICE_INSTANE("monitoring.webapp.ui.serviceInstance", "Service Instance"),
        //
        USER("monitoring.webapp.ui.user", "User"),
        //
        USER_NAME("monitoring.webapp.ui.user", "Login"),
        //
        CONTACT_NAME("monitoring.webapp.ui.contactName", "Contact"),
        //
        DISPLAY_NAME("monitoring.webapp.ui.displayname", "Name"),
        //
        EMAIL("monitoring.webapp.ui.email", "Email"),
        //
        OPERATIN("monitoring.webapp.ui.operation", "Operation"),
        //
        SUBSCRIBE("monitoring.webapp.ui.subscribe", "Subscribe"),
        //
        NODES("monitoring.webapp.ui.nodes", "Select Node"),
        //
        TEXT("monitoring.webapp.ui.text", "Text"),
        //
        STOP("monitoring.webapp.ui.stop", "Stop"),
        //
        USE_REGEX("monitoring.webapp.ui.useRegex", "Use Regex"),
        //
        FILTER("monitoring.webapp.ui.filter", "Filter"),
        //
        CHANGE_USER("monitoring.webapp.ui.change_user", "Change User"),
        //
        REMOVE_ALL("monitoring.webapp.ui.remove.all", "Remove all"),
        //
        REMOVE_SELECTED("monitoring.webapp.ui.remove.selected", "Remove selected"),
        //
        TAILORED("monitoring.webapp.ui.tailored", "Tailored");

        private final String message;

        MESSAGE(String key, String defaultValue) {
            this.message = getString(key, defaultValue);
        }

        public String getMessage() {
            return message;
        }
    }

    private static final Locale CURRENT_LOCALE = new Locale("en", "US");

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("MessagesBundle", CURRENT_LOCALE);

    private Messages() {

    }

    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (final MissingResourceException e) {
            return StringUtils.EMPTY;
        }
    }

    public static String getString(final String key, final String defaultValue) {
        String value = Messages.getString(key);
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }

}
