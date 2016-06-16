package monitoring.webapp.ui.ep.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.data.Connection;
import common.data.dto.LogDTO;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.view.EpView;
import monitoring.webapp.ui.ep.view.EpView.EPViewListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class EpViewPresenter extends AbstractPresenter<MonitoringService, EpView> implements EPViewListener {

    private long epId;

    private Connection thisOne;

    public EpViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, EpView userInterface) {

        userInterface.initBreadcrumb().add("Event Processing");
        userInterface.addEPViewListener(this);

        List<Connection> listEP = model.listEP();

        thisOne = new Connection();
        for (Connection ep : listEP) {
            if (ep.getId() == epId) {
                thisOne = ep;
                break;
            }
        }

        userInterface.setConnection(thisOne);
        userInterface.setQueryTable(model.listRegisteredQuery(thisOne));
        userInterface.setRuleTable(model.listRegisteredRule(thisOne));
        userInterface.setLogTableTriggeredAction(getRuleTriggerdLogs());

    }

    private List<LogDTO> getRuleTriggerdLogs() {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("CALLER_METHOD", "trigger");
        parameters.put("CALLER_CLASS", "event.processing.rule.RuleTrigger");
        parameters.put("ARG2", thisOne.getUrl().getAuthority());

        return getModel().getAllLog(parameters);
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

    @Override
    public void refresh() {
        getUserInterface().getLogTableTriggeredAction().removeAllBeanItems();
        getUserInterface().getLogTableTriggeredAction().addBeanItems(getRuleTriggerdLogs());
    }

}
