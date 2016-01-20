package monitoring.webapp.ui.log.presenter;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.log.view.LogView;
import monitoring.webapp.ui.log.view.LogView.LogViewListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class LogViewPresenter extends AbstractPresenter<MonitoringService, LogView> implements LogViewListener {

    private static int filter;

    public LogViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, LogView userInterface) {

        userInterface.initBreadcrumb().add("Logs");
        userInterface.getLogTable().addBeanItems(getMonitoringService().getAllLog());
        userInterface.addLogViewListener(this);
    }

    @Override
    protected void initUserInterface(LogView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(MonitoringService model) {
        // TODO Auto-generated method stub

    }

    @Override
    public void refresh() {
        getUserInterface().getLogTable().removeAllBeanItems();
        getUserInterface().getLogTable().addBeanItems(getMonitoringService().getFilteredLog(filter));
    }

    @Override
    public void delete() {

        getUserInterface().getLogTable().removeAllBeanItems();

        getMonitoringService().deleteAllLog();

        getUserInterface().getLogTable().addBeanItems(getMonitoringService().getAllLog());
    }

    @Override
    public void filterEP() {
        filter(0);
    }

    @Override
    public void filterCM() {
        filter(1);
    }

    @Override
    public void filterDEV() {
        filter(2);
    }

    private void filter(int pos) {
        filter = toggleBit(filter, pos);
        getUserInterface().getLogTable().removeAllBeanItems();
        getUserInterface().getLogTable().addBeanItems(getMonitoringService().getFilteredLog(filter));
    }

    public static int toggleBit(int value, int pos) {

        if ((value & (1L << pos)) != 0) {
            value = value & ~(1 << pos);
        } else {
            value = value | (1 << pos);
        }
        return value;
    }
}
