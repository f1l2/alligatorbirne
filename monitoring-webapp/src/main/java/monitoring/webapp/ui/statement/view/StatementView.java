package monitoring.webapp.ui.statement.view;

import monitoring.webapp.ui.statement.component.QueryComponent;
import monitoring.webapp.ui.statement.component.RuleComponent;
import monitoring.webapp.ui.view.AbstractView;

public interface StatementView extends AbstractView {
    public static final String VIEW_NAME = "/statement";

    public QueryComponent getQueryComponent();

    public RuleComponent getRuleComponent();

}
