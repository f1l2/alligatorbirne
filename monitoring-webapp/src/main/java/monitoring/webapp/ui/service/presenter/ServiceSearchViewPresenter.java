package monitoring.webapp.ui.service.presenter;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.presenter.AbstractPresenter;
import monitoring.webapp.ui.service.view.ServiceSearchView;

public class ServiceSearchViewPresenter extends AbstractPresenter<Connection, ServiceSearchView> {

    private final EPTablePresenter epTablePresenter;

    public ServiceSearchViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
        epTablePresenter = new EPTablePresenter(monitoringService);

    }

    @Override
    protected void init(Connection model, ServiceSearchView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initUserInterface(ServiceSearchView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(Connection model) {
        // TODO Auto-generated method stub

    }
}
