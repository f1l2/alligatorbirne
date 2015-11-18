package monitoring.webapp.ui.statement.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.navigator.StatementNavigator;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.statement.component.QueryComponent;
import monitoring.webapp.ui.statement.component.QueryComponentImpl;
import monitoring.webapp.ui.statement.component.RuleComponent;
import monitoring.webapp.ui.statement.component.RuleComponentImpl;
import monitoring.webapp.ui.statement.presenter.StatementViewPresenter;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(StatementView.VIEW_NAME)
public class StatementViewImpl extends AbstractViewImpl<StatementNavigator>implements StatementView {

    private QueryComponentImpl queryComponentImpl;

    private RuleComponentImpl ruleComponentImpl;

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
    }

    @Override
    protected void init(MonitoringService monitoringService) {

    }

    @Override
    protected void init(VerticalLayout layout) {

        queryComponentImpl = new QueryComponentImpl();
        ruleComponentImpl = new RuleComponentImpl();

        final Label lbRq = new Label(("Registered Quer(y/ies)"));
        lbRq.addStyleName("monitoring-label-monospace");

        final Label lbRr = new Label("Registered Rule(s)");
        lbRr.addStyleName("monitoring-label-monospace");

        final VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        vLayout.setMargin(true);

        vLayout.addComponent(lbRq);
        vLayout.addComponent(UiUtils.panelWrapper(queryComponentImpl));
        vLayout.addComponent(lbRr);
        vLayout.addComponent(UiUtils.panelWrapper(ruleComponentImpl));

        layout.addComponent(vLayout);

    }

    @Override
    protected void enter(StatementNavigator navigator, MonitoringService monitoringService) {
        final StatementViewPresenter statementViewPresenter = new StatementViewPresenter(monitoringService);
        statementViewPresenter.setModel(monitoringService);
        statementViewPresenter.setUserInterface(this);
    }

    @Override
    protected StatementNavigator createInstance(String parameters) {
        return new StatementNavigator(parameters);
    }

    @Override
    public QueryComponent getQueryComponent() {
        return queryComponentImpl;
    }

    @Override
    public RuleComponent getRuleComponent() {
        return ruleComponentImpl;
    }
}
