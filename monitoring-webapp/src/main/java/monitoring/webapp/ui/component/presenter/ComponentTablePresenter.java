package monitoring.webapp.ui.component.presenter;

import java.util.Collection;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.component.component.ComponentTable;
import monitoring.webapp.ui.table.presenter.BeanItemTablePresenter;

public class ComponentTablePresenter extends BeanItemTablePresenter<Connection, ComponentTable> {

    public ComponentTablePresenter(MonitoringService monitoringService) {
        super(monitoringService);

    }

    @Override
    protected void init(Collection<Connection> beanItems, ComponentTable table) {

        table.setBeanItems(beanItems);

    }

    @Override
    protected void initUserInterface(ComponentTable userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(Collection<Connection> model) {
        // TODO Auto-generated method stub

    }

}
