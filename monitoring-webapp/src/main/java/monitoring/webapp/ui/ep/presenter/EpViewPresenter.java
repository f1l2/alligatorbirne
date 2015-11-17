package monitoring.webapp.ui.ep.presenter;

import java.util.List;

import common.data.Connection;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.view.EpView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class EpViewPresenter extends AbstractPresenter<MonitoringService, EpView> {

    private long epId;

    public EpViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, EpView userInterface) {

        userInterface.initBreadcrumb().add("Event Processing");

        List<Connection> listEP = model.listEP();

        Connection thisOne = new Connection();
        for (Connection ep : listEP) {
            if (ep.getId() == epId) {
                thisOne = ep;
                break;
            }
        }

        userInterface.setConnection(thisOne);
        userInterface.setQueryTable(model.listRegisteredQuery(thisOne));
        userInterface.setRuleTable(model.listRegisteredRule(thisOne));

    }

    @Override
    protected void initUserInterface(EpView userInterface) {
    }

    @Override
    protected void initModel(MonitoringService model) {
    }

    public long getEpId() {
        return epId;
    }

    public void setEpId(long epId) {
        this.epId = epId;
    }

}
