package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.ep.view.EPView;

public class EPNavigator extends AbstractNavigator {

    public EPNavigator(String parameters) {
        super(parameters);
    }

    public EPNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return EPView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
