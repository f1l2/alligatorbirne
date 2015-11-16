package monitoring.webapp.ui.ep.presenter;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.view.EPView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class ServiceSearchViewPresenter extends AbstractPresenter<Connection, EPView> {

    private final EPTablePresenter epTablePresenter;

    public ServiceSearchViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
        epTablePresenter = new EPTablePresenter(monitoringService);

    }

    @Override
    protected void init(Connection model, EPView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initUserInterface(EPView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(Connection model) {
        // TODO Auto-generated method stub

    }
}
