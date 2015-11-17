package monitoring.webapp.ui.component.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.component.component.ComponentTable;
import monitoring.webapp.ui.component.component.ComponentTableImpl;
import monitoring.webapp.ui.component.presenter.ComponentViewPresenter;
import monitoring.webapp.ui.navigator.ComponentNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(ComponentView.VIEW_NAME)
public class ComponentViewImpl extends AbstractViewImpl<ComponentNavigator>implements ComponentView {

    private ComponentTableImpl epTableImpl;

    private ComponentTableImpl devTableImpl;

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(MonitoringService monitoringService) {

    }

    @Override
    protected void init(VerticalLayout layout) {

        epTableImpl = new ComponentTableImpl();
        epTableImpl.setSelectable(false);
        epTableImpl.setImmediate(true);
        epTableImpl.setColumnExpandRatio(ComponentTable.COLUMN.URL, 1.0f);
        epTableImpl.setWidth(100, Unit.PERCENTAGE);
        epTableImpl.addStyleName("virtual-table-min-width");

        final Panel panelEP = UiUtils.panelWrapper(epTableImpl, "EP");

        devTableImpl = new ComponentTableImpl();
        devTableImpl.setSelectable(false);
        devTableImpl.setImmediate(true);
        devTableImpl.setColumnExpandRatio(ComponentTable.COLUMN.URL, 1.0f);
        devTableImpl.setWidth(100, Unit.PERCENTAGE);
        devTableImpl.addStyleName("virtual-table-min-width");

        final Panel panelDEV = UiUtils.panelWrapper(devTableImpl, "Dev");

        Label lbDev = new Label(("Device Instance(s)"));
        lbDev.addStyleName("monitoring-label-monospace");

        Label lbEP = new Label("Event Processing Instance(s)");
        lbEP.addStyleName("monitoring-label-monospace");

        layout.setMargin(new MarginInfo(true, false, false, false));
        layout.setSpacing(true);
        layout.addComponent(lbDev);
        layout.addComponent(panelDEV);
        layout.addComponent(lbEP);
        layout.addComponent(panelEP);
    }

    @Override
    protected void enter(ComponentNavigator navigator, MonitoringService monitoringService) {
        final ComponentViewPresenter componentViewPresenter = new ComponentViewPresenter(monitoringService);
        componentViewPresenter.setUserInterface(this);

        componentViewPresenter.setModel(monitoringService);

    }

    @Override
    public ComponentTable getEPTable() {
        return epTableImpl;
    }

    @Override
    public ComponentTable getDevTable() {
        return devTableImpl;
    }

    @Override
    protected ComponentNavigator createInstance(String parameters) {
        return new ComponentNavigator(parameters);
    }

}
