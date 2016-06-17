package monitoring.webapp.ui.dev.presenter;

import java.util.List;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.dev.component.DataSourceTable.DataSourceTableListener;
import monitoring.webapp.ui.dev.view.DevView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class DevViewPresenter extends AbstractPresenter<MonitoringService, DevView> implements DataSourceTableListener {

    private long devId;

    public DevViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, DevView userInterface) {

        userInterface.initBreadcrumb().add("Device");

        List<Connection> listDev = model.listDev();

        Connection thisOne = new Connection();
        for (Connection ep : listDev) {
            if (ep.getId() == devId) {
                thisOne = ep;
                break;
            }
        }

        userInterface.setConnection(thisOne);
        userInterface.setDataSources(model.listDataSourcesById(thisOne));
        userInterface.getDataSourcesTable().addListener(this);

    }

    @Override
    protected void initUserInterface(DevView userInterface) {
    }

    @Override
    protected void initModel(MonitoringService model) {
    }

    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    @Override
    public void simulate(String device, String domain, String value) {
        getMonitoringService().simulateDeviceData(device, domain, value);
    }
}
