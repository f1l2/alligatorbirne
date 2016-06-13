
package monitoring.webapp.ui.statement.presenter;

import java.util.List;

import common.codes.SUCCESS_CODES;
import common.data.dto.RuleDTO;
import common.selection.DISTRIBUTION_STRATEGY;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.component.RuleTable.RuleTableListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;
import monitoring.webapp.ui.statement.component.RuleComponent;
import monitoring.webapp.ui.statement.component.RuleComponent.RuleComponentListener;

public class RuleComponentPresenter extends AbstractPresenter<List<RuleDTO>, RuleComponent> implements RuleComponentListener, RuleTableListener {

    public RuleComponentPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    public void save(String name, String rule) {

        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setName(name);
        ruleDTO.setRule(rule);

        String response = getMonitoringService().registerRule(name, rule);

        if (SUCCESS_CODES.OK.getMessage().equals(response)) {
            getUserInterface().getRuleTable().addBeanItem(ruleDTO);
        }

    }

    @Override
    protected void init(List<RuleDTO> model, RuleComponent userInterface) {

        userInterface.getRuleTable().addBeanItems(model);
        userInterface.getRuleTable().addRuleTableListener(this);
    }

    @Override
    protected void initUserInterface(RuleComponent userInterface) {
        userInterface.addRuleComponentListener(this);
        userInterface.setPreparedRules(getMonitoringService().getPreparedRules());
    }

    @Override
    protected void initModel(List<RuleDTO> model) {

    }

    @Override
    public void activate(RuleDTO rule) {

        DISTRIBUTION_STRATEGY strategy = DISTRIBUTION_STRATEGY.getByDescription(getUserInterface().getCbStrategy().getCaption());

        String result = getMonitoringService().activateRule(rule, strategy.getNumber());

        if (SUCCESS_CODES.OK.getMessage().equals(result)) {
            getUserInterface().getRuleTable().removeAllBeanItems();
            getUserInterface().getRuleTable().addBeanItems(getMonitoringService().listRegisteredRule());
        }
    }

    @Override
    public void deactivate(RuleDTO rule) {
        String result = getMonitoringService().deactivateRule(rule);
        if (SUCCESS_CODES.OK.getMessage().equals(result)) {
            getUserInterface().getRuleTable().removeAllBeanItems();
            getUserInterface().getRuleTable().addBeanItems(getMonitoringService().listRegisteredRule());
        }

    }

    @Override
    public void delete(RuleDTO rule) {
        getMonitoringService().deregisterRule(rule);
        getUserInterface().getRuleTable().removeAllBeanItems();
        getUserInterface().getRuleTable().addBeanItems(getMonitoringService().listRegisteredRule());
    }
}
