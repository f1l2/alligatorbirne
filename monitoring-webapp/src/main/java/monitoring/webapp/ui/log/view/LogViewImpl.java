package monitoring.webapp.ui.log.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.annotations.Push;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.log.component.LogTableImpl;
import monitoring.webapp.ui.log.presenter.LogViewPresenter;
import monitoring.webapp.ui.navigator.LogNavigator;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(LogView.VIEW_NAME)
@Push
public class LogViewImpl extends AbstractViewImpl<LogNavigator> implements LogView {

    private EventListenerManager<LogViewListener> eventListenerManager = new EventListenerManager<LogViewListener>();

    private LogTableImpl logTable;

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {

        HorizontalLayout hLayout = new HorizontalLayout();

        Button deleteButton = UiUtils.newButton(ICON.REMOVE);
        deleteButton.setDescription("Delete");
        deleteButton.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.delete());
        });

        Button refreshButton = UiUtils.newButton(ICON.UPDATE);
        refreshButton.setDescription("Refresh");
        refreshButton.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.refresh());
        });

        CheckBox epCB = new CheckBox("EP log");
        epCB.addValueChangeListener(item -> {
            eventListenerManager.fireEvent(i -> i.filterEP());
        });
        CheckBox cmCB = new CheckBox("CM log");
        cmCB.addValueChangeListener(item -> {
            eventListenerManager.fireEvent(i -> i.filterCM());
        });

        CheckBox devCB = new CheckBox("DEV log");
        devCB.addValueChangeListener(item -> {
            eventListenerManager.fireEvent(i -> i.filterDEV());
        });

        HorizontalLayout hLayoutCB = new HorizontalLayout();
        hLayoutCB.setSizeFull();
        hLayoutCB.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        hLayoutCB.setSpacing(true);
        hLayoutCB.addComponent(new Label("Filter:"));
        hLayoutCB.addComponent(epCB);
        hLayoutCB.addComponent(cmCB);
        hLayoutCB.addComponent(devCB);

        hLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        hLayout.addComponent(refreshButton);
        hLayout.addComponent(deleteButton);
        hLayout.addComponent(hLayoutCB);
        hLayout.setSpacing(true);

        logTable = new LogTableImpl();
        logTable.setSelectable(false);
        logTable.setImmediate(true);
        logTable.setColumnExpandRatio(LogTable.COLUMN.MESSAGE, 1.0f);
        logTable.setWidth(100, Unit.PERCENTAGE);
        logTable.addStyleName("virtual-table-min-width");

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setMargin(true);
        vLayout.setSpacing(true);

        vLayout.addComponent(hLayout);
        vLayout.addComponent(logTable);

        layout.addComponent(vLayout);

    }

    @Override
    protected void enter(LogNavigator navigator, MonitoringService monitoringService) {
        LogViewPresenter logViewPresenter = new LogViewPresenter(monitoringService);
        logViewPresenter.setModel(monitoringService);
        logViewPresenter.setUserInterface(this);

    }

    @Override
    protected LogNavigator createInstance(String parameters) {
        return new LogNavigator(parameters);
    }

    @Override
    public LogTable getLogTable() {
        return logTable;
    }

    @Override
    public void addLogViewListener(LogViewListener listener) {
        this.eventListenerManager.addEventListener(listener);
    }

}
