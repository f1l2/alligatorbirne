package monitoring.webapp.ui.messaging.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.navigator.MessagingNavigator;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(MessagingView.VIEW_NAME)
public class MessagingViewImpl extends AbstractViewImpl<MessagingNavigator>implements MessagingView {

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void enter(MessagingNavigator navigator, MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected MessagingNavigator createInstance(String parameters) {
        // TODO Auto-generated method stub
        return null;
    }

}
