package monitoring.webapp.ui.navigator;

import com.vaadin.server.VaadinServlet;

import monitoring.webapp.ui.browser.model.BrowserResource;
import monitoring.webapp.ui.browser.view.BrowserView;

public class BrowserNavigator extends AbstractNavigator {
    private static final int NAME = 0;

    public BrowserNavigator(String parameters) {
        super(parameters);
    }

    public BrowserNavigator(BrowserResource browserResource) {
        super(browserResource.getName() + "/" + browserResource.getUri());
    }

    public BrowserResource getBrowserResource() {

        StringBuilder urlBuilder = new StringBuilder(VaadinServlet.getCurrent().getServletContext().getContextPath());

        String parameter = null;
        for (int i = 1; (parameter = getParamter(i)) != null; i++) {
            urlBuilder.append('/').append(parameter);
        }

        return new BrowserResource(getParamter(NAME), urlBuilder.toString());
    }

    @Override
    protected String getViewName() {
        return BrowserView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
