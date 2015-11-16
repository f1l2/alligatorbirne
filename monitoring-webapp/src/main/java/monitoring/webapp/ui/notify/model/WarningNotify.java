package monitoring.webapp.ui.notify.model;

public class WarningNotify extends NotifyModel<WarningNotify> {

    private static final String STYLE = "humanized warning";
    private static final String CAPTION = "Warning";

    public WarningNotify() {
        setCaption(CAPTION).setStyle(STYLE);
    }

}
