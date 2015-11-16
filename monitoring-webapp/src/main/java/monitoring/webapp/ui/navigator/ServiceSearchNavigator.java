package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.service.view.ServiceSearchView;

public class ServiceSearchNavigator extends AbstractNavigator {

    public ServiceSearchNavigator(String parameters) {
        super(parameters);
    }

    public ServiceSearchNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return ServiceSearchView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
