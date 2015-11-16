package monitoring.webapp.ui.notify.model;

public class SuccessNotify extends NotifyModel<SuccessNotify> {

    private static final String STYLE = "humanized success";
    private static final String CAPTION = "Success";

    public SuccessNotify() {
        setCaption(CAPTION).setStyle(STYLE);
    }

}
