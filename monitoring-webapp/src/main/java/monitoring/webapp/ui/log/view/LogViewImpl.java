package monitoring.webapp.ui.log.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.log.component.LogTableImpl;
import monitoring.webapp.ui.log.presenter.LogViewPresenter;
import monitoring.webapp.ui.navigator.LogNavigator;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(LogView.VIEW_NAME)
public class LogViewImpl extends AbstractViewImpl<LogNavigator>implements LogView {

    private LogTableImpl logTable;

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {

        logTable = new LogTableImpl();
        logTable.setSelectable(false);
        logTable.setImmediate(true);
        logTable.setColumnExpandRatio(LogTable.COLUMN.MESSAGE, 1.0f);
        logTable.setWidth(100, Unit.PERCENTAGE);
        logTable.addStyleName("virtual-table-min-width");

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setMargin(true);
        vLayout.setSpacing(true);

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

}
