package monitoring.webapp.ui.component.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.component.component.ComponentTable;
import monitoring.webapp.ui.component.component.ComponentTableImpl;
import monitoring.webapp.ui.component.presenter.ComponentViewPresenter;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.navigator.ComponentNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(ComponentView.VIEW_NAME)
public class ComponentViewImpl extends AbstractViewImpl<ComponentNavigator> implements ComponentView {

    private EventListenerManager<ComponentViewListener> eventListenerManager = new EventListenerManager<ComponentViewListener>();

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

        Button refreshEPButton = UiUtils.newButton(ICON.UPDATE);
        refreshEPButton.setDescription("Refresh");
        refreshEPButton.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.refreshEPTable());
        });

        HorizontalLayout epTableActions = new HorizontalLayout();
        epTableActions.addComponent(refreshEPButton);

        Button refreshDevButton = UiUtils.newButton(ICON.UPDATE);
        refreshDevButton.setDescription("Refresh");
        refreshDevButton.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.refreshDevTable());
        });

        HorizontalLayout devTableActions = new HorizontalLayout();
        devTableActions.addComponent(refreshDevButton);

        epTableImpl = new ComponentTableImpl();
        epTableImpl.setCaption("Event Processing Instance(s)");
        epTableImpl.addStyleName("monitoring-label-monospace");
        epTableImpl.setSelectable(false);
        epTableImpl.setImmediate(true);
        epTableImpl.setColumnExpandRatio(ComponentTable.COLUMN.URL, 1.0f);
        epTableImpl.setWidth(100, Unit.PERCENTAGE);
        epTableImpl.addStyleName("virtual-table-min-width");

        devTableImpl = new ComponentTableImpl();
        devTableImpl.setCaption("Device Instance(s)");
        devTableImpl.addStyleName("monitoring-label-monospace");
        devTableImpl.setSelectable(false);
        devTableImpl.setImmediate(true);
        devTableImpl.setColumnExpandRatio(ComponentTable.COLUMN.URL, 1.0f);
        devTableImpl.setWidth(100, Unit.PERCENTAGE);
        devTableImpl.addStyleName("virtual-table-min-width");

        final FormLayout formLayout = new FormLayout();
        formLayout.addComponent(devTableActions);
        formLayout.addComponent(devTableImpl);
        formLayout.addComponent(epTableActions);
        formLayout.addComponent(epTableImpl);

        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(formLayout);
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

    @Override
    public void addComponentViewListener(ComponentViewListener listener) {
        eventListenerManager.addEventListener(listener);
    }

}
