package configuration.management.rest.activity.model;

import common.data.Connection;
import configuration.management.model.RuleDLO;

public class RevokeRuleItem {

    public RevokeRuleItem(RuleDLO rule, Connection ep) {
        super();
        this.rule = rule;
        this.ep = ep;
    }

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
