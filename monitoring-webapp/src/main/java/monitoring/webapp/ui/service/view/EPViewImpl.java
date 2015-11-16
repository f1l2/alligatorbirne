package monitoring.webapp.ui.service.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.navigator.ServiceSearchNavigator;
import monitoring.webapp.ui.service.component.EPTable;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
public class EPViewImpl extends AbstractViewImpl<ServiceSearchNavigator>implements ServiceSearchView {

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void enter(ServiceSearchNavigator navigator, MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    public EPTable getServiceTable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void init(VerticalLayout layout) {
        // TODO Auto-generated method stub

    }

    @Override
    protected ServiceSearchNavigator createInstance(String parameters) {
        // TODO Auto-generated method stub
        return null;
    }
}
