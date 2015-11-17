package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.component.view.ComponentView;

public class ComponentNavigator extends AbstractNavigator {

    private static final long EP_ID = 0;

    private static final long DEV_ID = 0;

    public ComponentNavigator(String parameters) {
        super(parameters);
    }

    public ComponentNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return ComponentView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

    public static long getEpId() {
        return EP_ID;
    }

    public static long getDevId() {
        return DEV_ID;
    }

}
