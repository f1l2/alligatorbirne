package monitoring.webapp.ui.statement.presenter;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.presenter.AbstractPresenter;
import monitoring.webapp.ui.statement.view.StatementView;

public class StatementViewPresenter extends AbstractPresenter<MonitoringService, StatementView> {

    private QueryComponentPresenter queryComponentPresenter;

    private RuleComponentPresenter ruleComponentPresenter;

    public StatementViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
        queryComponentPresenter = new QueryComponentPresenter(monitoringService);
        ruleComponentPresenter = new RuleComponentPresenter(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, StatementView userInterface) {
        userInterface.initBreadcrumb().add("Query / Rule Mgmt");

        queryComponentPresenter.setModel(model.listRegisteredQuery());
        queryComponentPresenter.setUserInterface(userInterface.getQueryComponent());

        ruleComponentPresenter.setModel(model.listRegisteredRule());
        ruleComponentPresenter.setUserInterface(userInterface.getRuleComponent());

    }

    @Override
    protected void initUserInterface(StatementView userInterface) {

    }

    @Override
    protected void initModel(MonitoringService model) {

    }
}
