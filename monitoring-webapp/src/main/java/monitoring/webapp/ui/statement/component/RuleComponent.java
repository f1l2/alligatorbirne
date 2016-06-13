package monitoring.webapp.ui.statement.component;

import java.util.EventListener;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

import monitoring.webapp.ui.ep.component.RuleTableImpl;
import monitoring.webapp.ui.notify.component.Notify;

public interface RuleComponent extends Notify {

    public RuleTableImpl getRuleTable();

    public Button getRuleAddButton();

    public void addRuleComponentListener(RuleComponentListener externalLinkComponentListener);

    public Window getAddRuleWindow();

    public void setPreparedRules(List<String> preparedRules);

    public static interface RuleComponentListener extends EventListener {

        public void save(String name, String query);
    }
}
