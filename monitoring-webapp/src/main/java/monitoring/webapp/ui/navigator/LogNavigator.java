package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.log.view.LogView;

public class LogNavigator extends AbstractNavigator {

    public LogNavigator(String parameters) {
        super(parameters);
    }

    public LogNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return LogView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
