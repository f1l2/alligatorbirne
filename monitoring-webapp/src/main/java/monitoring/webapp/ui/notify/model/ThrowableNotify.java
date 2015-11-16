package monitoring.webapp.ui.notify.model;

import org.apache.commons.lang3.StringUtils;

public class ThrowableNotify extends NotifyModel<ThrowableNotify> {

    private static final String STYLE = "humanized error";
    private static final String CAPTION = "Error";
    private static final String DESCRIPTION = "An unexpected error occurred.";

    private Throwable throwable;

    public ThrowableNotify(Throwable throwable) {
        this();
        setThrowable(throwable);
    }

    public ThrowableNotify() {
        setCaption(CAPTION).setDescription(DESCRIPTION).setStyle(STYLE);
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("");

        description.append(StringUtils.trimToEmpty(super.getDescription()));

        if (throwable != null) {
            if (description.length() > 0) {
                description.append('\n');
            }
            description.append(throwable.toString());
        }

        return description.toString();
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ThrowableNotify setThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

}
