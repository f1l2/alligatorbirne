package monitoring.webapp.ui.browser.view;

import monitoring.webapp.ui.view.AbstractView;

public interface BrowserView extends AbstractView {
    public static final String VIEW_NAME = "/browser";

    public void setUrl(String url);

}
