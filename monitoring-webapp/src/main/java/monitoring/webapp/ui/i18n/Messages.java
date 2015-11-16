package monitoring.webapp.ui.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

public final class Messages {

    public enum MESSAGE {
        DISCARD("at.a1ta.esb.dp.ui.discard", "Discard"),
        //
        SAVE("at.a1ta.esb.dp.ui.save", "Save"),
        //
        CANCEL("at.a1ta.esb.dp.ui.cancel", "Cancel"),
        //
        CODE("at.a1ta.esb.dp.ui.code", "Code"),
        //
        MESSAGE("at.a1ta.esb.dp.ui.message", "Message"),
        //
        NAME("at.a1ta.esb.dp.ui.name", "Name"),
        //
        MEP("at.a1ta.esb.dp.ui.mep", "MEP"),
        //
        DESCRIPTION("at.a1ta.esb.dp.ui.description", "Description"),
        //
        ADD("at.a1ta.esb.dp.ui.add", "Add"),
        //
        RESET("at.a1ta.esb.dp.ui.reset", "Reset"),
        //
        APPLY("at.a1ta.esb.dp.ui.apply", "Apply"),
        //
        UPDATE("at.a1ta.esb.dp.ui.update", "Update"),
        //
        REFRESH("at.a1ta.esb.dp.ui.refresh", "Refresh"),
        //
        KEYWORD("at.a1ta.esb.dp.ui.keyword", "Keyword"),
        //
        SHOW_AT_LEAST("at.a1ta.esb.dp.ui.showAtLeast", "Show at least"),
        //
        ESB_NODE("at.a1ta.esb.dp.ui.esbNode", "ESB Node"),
        //
        RELEVANCE("at.a1ta.esb.dp.ui.relevance", "Relevance"),
        //
        DEPLOY("at.a1ta.esb.dp.ui.deploy", "Deploy"),
        //
        SERVICE_TYPE("at.a1ta.esb.dp.ui.serviceType", "Service Type"),
        //
        LABELS("at.a1ta.esb.dp.ui.labels", "Labels"),
        //
        OK("at.a1ta.esb.dp.ui.ok", "OK"),
        //
        ENVIRONMENT("at.a1ta.esb.dp.ui.environment", "Environment"),
        //
        FROM("at.a1ta.esb.dp.ui.environment", "From"),
        //
        SERVICE("at.a1ta.esb.dp.ui.service", "Service"),
        //
        SERVICE_INSTANE("at.a1ta.esb.dp.ui.serviceInstance", "Service Instance"),
        //
        USER("at.a1ta.esb.dp.ui.user", "User"),
        //
        USER_NAME("at.a1ta.esb.dp.ui.user", "Login"),
        //
        CONTACT_NAME("at.a1ta.esb.dp.ui.contactName", "Contact"),
        //
        DISPLAY_NAME("at.a1ta.esb.dp.ui.displayname", "Name"),
        //
        EMAIL("at.a1ta.esb.dp.ui.email", "Email"),
        //
        OPERATIN("at.a1ta.esb.dp.ui.operation", "Operation"),
        //
        SUBSCRIBE("at.a1ta.esb.dp.ui.subscribe", "Subscribe"),
        //
        NODES("at.a1ta.esb.dp.ui.nodes", "Select Node"),
        //
        TEXT("at.a1ta.esb.dp.ui.text", "Text"),
        //
        STOP("at.a1ta.esb.dp.ui.stop", "Stop"),
        //
        USE_REGEX("at.a1ta.esb.dp.ui.useRegex", "Use Regex"),
        //
        FILTER("at.a1ta.esb.dp.ui.filter", "Filter"),
        //
        CHANGE_USER("at.a1ta.esb.dp.ui.change_user", "Change User"),
        //
        REMOVE_ALL("at.a1ta.esb.dp.ui.remove.all", "Remove all"),
        //
        REMOVE_SELECTED("at.a1ta.esb.dp.ui.remove.selected", "Remove selected"),
        //
        TAILORED("at.a1ta.esb.dp.ui.tailored", "Tailored");

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
