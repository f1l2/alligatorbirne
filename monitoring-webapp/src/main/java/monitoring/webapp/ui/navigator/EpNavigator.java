package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.ep.view.EpView;

public class EpNavigator extends AbstractNavigator {

    private static final int EP_ID = 0;

    public EpNavigator(String parameters) {
        super(parameters);
    }

    public EpNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return EpView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

    public String getEpId() {
        return getParamter(EP_ID);
    }
}
