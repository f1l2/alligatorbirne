package monitoring.webapp.ui.service.presenter;

import java.util.Collection;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.service.component.EPTable;
import monitoring.webapp.ui.table.presenter.BeanItemTablePresenter;

public class EPTablePresenter extends BeanItemTablePresenter<Connection, EPTable> {

    public EPTablePresenter(MonitoringService monitoringService) {
        super(monitoringService);

    }

    @Override
    protected void init(Collection<Connection> model, EPTable userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initUserInterface(EPTable userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(Collection<Connection> model) {
        // TODO Auto-generated method stub

    }

}
