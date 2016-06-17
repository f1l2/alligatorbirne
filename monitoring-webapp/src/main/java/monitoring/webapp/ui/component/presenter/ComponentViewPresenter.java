package monitoring.webapp.ui.component.presenter;

import java.util.List;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.component.view.ComponentView;
import monitoring.webapp.ui.component.view.ComponentView.ComponentViewListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class ComponentViewPresenter extends AbstractPresenter<MonitoringService, ComponentView> implements ComponentViewListener {

    private final ComponentTablePresenter epTablePresenter;

    private final ComponentTablePresenter devTablePresenter;

    public ComponentViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
        epTablePresenter = new ComponentTablePresenter(monitoringService);
        devTablePresenter = new ComponentTablePresenter(monitoringService);

    }

    @Override
    protected void init(MonitoringService model, ComponentView userInterface) {
        userInterface.addComponentViewListener(this);
    }

    @Override
    protected void initModel(MonitoringService model) {

        epTablePresenter.setModel(listEP());
        devTablePresenter.setModel(listDev());

    }

    private List<Connection> listEP() {
        List<Connection> listEP = getModel().listEP();
        listEP.forEach(item -> item.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING));
        return listEP;
    }

    private List<Connection> listDev() {
        List<Connection> listDev = getModel().listDev();
        listDev.forEach(item -> item.setComponentType(COMPONENT_TYPE.DEVICE));

        return listDev;
    }

    @Override
    protected void initUserInterface(ComponentView userInterface) {
        epTablePresenter.setUserInterface(userInterface.getEPTable());
        devTablePresenter.setUserInterface(userInterface.getDevTable());

        userInterface.initBreadcrumb().add("Browse Components");
    }

    @Override
    public void refreshDevTable() {
        devTablePresenter.setModel(listDev());

    }

    @Override
    public void refreshEPTable() {
        epTablePresenter.setModel(listEP());
    }

}
