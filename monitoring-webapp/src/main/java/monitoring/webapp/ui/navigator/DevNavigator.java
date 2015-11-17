package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.component.view.DevView;

public class DevNavigator extends AbstractNavigator {

    private static final int DEV_ID = 0;

    public DevNavigator(String parameters) {
        super(parameters);
    }

    public DevNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return DevView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

    public String getDevId() {
        return getParamter(DEV_ID);
    }

}
