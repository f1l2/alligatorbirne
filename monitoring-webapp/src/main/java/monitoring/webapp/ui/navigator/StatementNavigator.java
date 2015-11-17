package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.statement.view.StatementView;

public class StatementNavigator extends AbstractNavigator {

    public StatementNavigator(String parameters) {
        super(parameters);
    }

    public StatementNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return StatementView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
