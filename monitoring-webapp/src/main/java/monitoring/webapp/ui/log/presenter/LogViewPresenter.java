package monitoring.webapp.ui.log.presenter;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.log.view.LogView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class LogViewPresenter extends AbstractPresenter<MonitoringService, LogView> {

    public LogViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, LogView userInterface) {
        userInterface.initBreadcrumb().add("Logs");

        userInterface.getLogTable().addBeanItems(getMonitoringService().listLogs());
    }

    @Override
    protected void initUserInterface(LogView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(MonitoringService model) {
        // TODO Auto-generated method stub

    }

}
