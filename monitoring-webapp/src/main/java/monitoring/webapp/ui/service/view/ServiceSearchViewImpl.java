package monitoring.webapp.ui.service.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.breadcrumb.component.Breadcrumb;
import monitoring.webapp.ui.navigator.ServiceSearchNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.service.component.EPTable;
import monitoring.webapp.ui.service.component.EPTableImpl;
import monitoring.webapp.ui.service.presenter.ServiceSearchViewPresenter;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(ServiceSearchView.VIEW_NAME)
public class ServiceSearchViewImpl extends AbstractViewImpl<ServiceSearchNavigator>implements ServiceSearchView {

    private EPTableImpl databaseTableImpl;

    @Override
    public Breadcrumb initBreadcrumb() {
        // TODO Auto-generated method stub
        return null;
    }

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
    protected void enter(ServiceSearchNavigator navigator, MonitoringService monitoringService) {
        final ServiceSearchViewPresenter serviceSearchViewPresenter = new ServiceSearchViewPresenter(monitoringService);
        serviceSearchViewPresenter.setUserInterface(this);

    }

    @Override
    protected ServiceSearchNavigator createInstance(String parameters) {
        return new ServiceSearchNavigator(parameters);
    }

}
