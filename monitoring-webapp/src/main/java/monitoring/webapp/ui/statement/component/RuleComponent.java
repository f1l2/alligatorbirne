package monitoring.webapp.ui.statement.component;

import java.util.EventListener;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Window;

import common.data.dto.RuleDTO;
import monitoring.webapp.ui.ep.component.RuleTableImpl;
import monitoring.webapp.ui.notify.component.Notify;

public interface RuleComponent extends Notify {

    public RuleTableImpl getRuleTable();

    public Button getRuleAddButton();

    public void addRuleComponentListener(RuleComponentListener externalLinkComponentListener);

    public Window getAddRuleWindow();

    public ComboBox getCbStrategy();

    public void setPreparedRules(List<RuleDTO> preparedRules);

    public static interface RuleComponentListener extends EventListener {

        public void save(String name, String query);
    }
}
