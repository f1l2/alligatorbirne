package monitoring.webapp.ui.ep.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.component.EPTable;
import monitoring.webapp.ui.ep.component.EPTableImpl;
import monitoring.webapp.ui.ep.presenter.ServiceSearchViewPresenter;
import monitoring.webapp.ui.navigator.EPNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(EPView.VIEW_NAME)
public class EPViewImpl extends AbstractViewImpl<EPNavigator>implements EPView {

    private EPTableImpl databaseTableImpl;

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        // TODO Auto-generated method stub

    }

    @Override
    public EPTable getServiceTable() {
        return databaseTableImpl;
    }

    @Override
    protected void init(MonitoringService monitoringService) {

    }

    @Override
    protected void init(VerticalLayout layout) {

        databaseTableImpl = new EPTableImpl();
        databaseTableImpl.setSelectable(false);
        databaseTableImpl.setImmediate(true);
        databaseTableImpl.setColumnExpandRatio(EPTable.COLUMN.URL, 1.0f);
        databaseTableImpl.setWidth(100, Unit.PERCENTAGE);
        databaseTableImpl.addStyleName("virtual-table-min-width");
        Label label = new Label("Hallo");

        final Panel panel = UiUtils.panelWrapper(databaseTableImpl, "Data");

        layout.addComponent(panel);
    }

    @Override
    protected void enter(EPNavigator navigator, MonitoringService monitoringService) {
        final ServiceSearchViewPresenter serviceSearchViewPresenter = new ServiceSearchViewPresenter(monitoringService);
        serviceSearchViewPresenter.setUserInterface(this);

    }

    @Override
    protected EPNavigator createInstance(String parameters) {
        return new EPNavigator(parameters);
    }

}
