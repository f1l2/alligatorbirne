package monitoring.webapp.ui.navigator;

import monitoring.webapp.ui.messaging.view.MessagingView;

public class MessagingNavigator extends AbstractNavigator {

    public MessagingNavigator(String parameters) {
        super(parameters);
    }

    public MessagingNavigator() {
        super();
    }

    @Override
    protected String getViewName() {
        return MessagingView.VIEW_NAME;
    }

    @Override
    protected boolean isLinkEnabled() {
        return true;
    }

}
