package monitoring.webapp.ui.component.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.navigator.DevNavigator;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(DevView.VIEW_NAME)
public class DevViewImpl extends AbstractViewImpl<DevNavigator>implements DevView {

    private String devId = null;

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {

        Label label = new Label(devId);

        layout.addComponent(label);

    }

    @Override
    protected void enter(DevNavigator navigator, MonitoringService monitoringService) {

        devId = navigator.getDevId();

    }

    @Override
    protected DevNavigator createInstance(String parameters) {
        return new DevNavigator(parameters);
    }

}
