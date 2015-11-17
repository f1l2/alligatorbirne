package monitoring.webapp.ui.component.presenter;

import java.util.List;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.component.view.ComponentView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class ComponentViewPresenter extends AbstractPresenter<MonitoringService, ComponentView> {

    private final ComponentTablePresenter epTablePresenter;

    private final ComponentTablePresenter devTablePresenter;

    public ComponentViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
        epTablePresenter = new ComponentTablePresenter(monitoringService);
        devTablePresenter = new ComponentTablePresenter(monitoringService);

    }

    @Override
    protected void init(MonitoringService model, ComponentView userInterface) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initModel(MonitoringService model) {

        List<Connection> listEP = model.listEP();
        List<Connection> listDev = model.listDev();

        listDev.forEach(item -> item.setComponentType(COMPONENT_TYPE.DEVICE));
        listEP.forEach(item -> item.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING));

        epTablePresenter.setModel(listEP);
        devTablePresenter.setModel(listDev);

    }

    @Override
    protected void initUserInterface(ComponentView userInterface) {
        epTablePresenter.setUserInterface(userInterface.getEPTable());
        devTablePresenter.setUserInterface(userInterface.getDevTable());

        userInterface.initBreadcrumb().add("Browse Components");
    }
}
