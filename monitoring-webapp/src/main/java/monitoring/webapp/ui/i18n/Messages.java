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
        MESSAGE("monitoring.webapp.ui.message", "Message"),
        //
        NAME("monitoring.webapp.ui.name", "Name"),
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
        OK("monitoring.webapp.ui.ok", "OK"),
        //
        TEXT("monitoring.webapp.ui.text", "Text"),
        //
        STOP("monitoring.webapp.ui.stop", "Stop"),
        //
        FILTER("monitoring.webapp.ui.filter", "Filter"),
        //
        REMOVE_ALL("monitoring.webapp.ui.remove.all", "Remove all"),
        //
        REMOVE_SELECTED("monitoring.webapp.ui.remove.selected", "Remove selected"),
        //
        COMPONENT_ID("monitoring.webapp.ui.component.id", "Id"),
        //
        COMPONENT_NAME("monitoring.webapp.ui.component.name", "Name"),
        //
        COMPONENT_URL("monitoring.webapp.ui.component.url", "Url"),
        //
        COMPONENT_UPDATED("monitoring.webapp.ui.component.updated", "Last updated"),
        //
        DESTINATION("monitoring.webapp.ui.messaging.destination", "Destination");

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
