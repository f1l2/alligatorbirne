package configuration.management.rest.activity.model;

import common.data.Connection;
import configuration.management.model.RuleDLO;

public class AssignRuleItem {

    private RuleDLO rule;

    private Connection ep;

    public RuleDLO getRule() {
        return rule;
    }

    public void setRule(RuleDLO rule) {
        this.rule = rule;
    }

    public Connection getEp() {
        return ep;
    }

    public void setEp(Connection ep) {
        this.ep = ep;
    }

}
