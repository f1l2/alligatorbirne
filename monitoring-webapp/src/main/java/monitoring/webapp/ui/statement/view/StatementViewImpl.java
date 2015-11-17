package monitoring.webapp.ui.statement.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.navigator.StatementNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(StatementView.VIEW_NAME)
public class StatementViewImpl extends AbstractViewImpl<StatementNavigator>implements StatementView {

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(MonitoringService monitoringService) {

    }

    @Override
    protected void init(VerticalLayout layout) {

    }

    @Override
    protected void enter(StatementNavigator navigator, MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected StatementNavigator createInstance(String parameters) {
        // TODO Auto-generated method stub
        return null;
    }
}
