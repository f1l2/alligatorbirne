package monitoring.webapp.ui.statement.component;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import monitoring.webapp.ui.ep.component.RuleTable;
import monitoring.webapp.ui.ep.component.RuleTableImpl;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.notify.component.NotifyComponent;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;

@SuppressWarnings("serial")
public class RuleComponentImpl extends NotifyComponent implements RuleComponent {

    private EventListenerManager<RuleComponentListener> eventListenerManager = new EventListenerManager<RuleComponentListener>();

    private RuleTableImpl queryTable;

    private Button addBtn;

    private List<String> preparedRules;

    public RuleComponentImpl() {

        addBtn = UiUtils.newButton(ICON.ADD);
        addBtn.setDescription("Add new external link");
        addBtn.addClickListener(listener -> {
            UI.getCurrent().addWindow(getAddRuleWindow());
        });

        queryTable = new RuleTableImpl();
        queryTable.setWidth(100, Unit.PERCENTAGE);
        queryTable = new RuleTableImpl();
        queryTable.setSelectable(false);
        queryTable.setImmediate(true);
        queryTable.setColumnExpandRatio(RuleTable.COLUMN.RULE, 1.0f);
        queryTable.setWidth(100, Unit.PERCENTAGE);
        queryTable.addStyleName("virtual-table-min-width");

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setMargin(true);
        btnLayout.addComponent(addBtn);
        btnLayout.addStyleName("monitoring-spacing-margin-inner");

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        vLayout.addComponent(queryTable);
        vLayout.addComponent(btnLayout);

        setCompositionRoot(vLayout);
    }

    @Override
    public Window getAddRuleWindow() {
        Window editorWindow = new Window();

        FormLayout editorForm = new FormLayout();

        TextField nameTxtField = new TextField("Name");
        TextField queryTxtField = new TextField("Rule");

        ComboBox ruleComboBox = new ComboBox("");
        preparedRules.stream().forEach(pq -> ruleComboBox.addItem(pq));

        nameTxtField.setWidth(100, Unit.PERCENTAGE);
        ruleComboBox.setWidth(100, Unit.PERCENTAGE);
        nameTxtField.setWidth(100, Unit.PERCENTAGE);
        queryTxtField.setWidth(100, Unit.PERCENTAGE);

        editorForm.addComponent(nameTxtField);
        editorForm.addComponent(queryTxtField);
        editorForm.addComponent(ruleComboBox);

        Button saveBtn = UiUtils.newButton(ICON.DISK);
        saveBtn.setDescription("Save");
        saveBtn.addClickListener(e -> {

            String rule = null;
            if ((null == ruleComboBox.getValue()) && (StringUtils.isBlank(queryTxtField.getValue()))) {
                // do nothing
            } else if (null != ruleComboBox.getValue()) {
                rule = ruleComboBox.getValue().toString();
            } else if (StringUtils.isNotBlank(queryTxtField.getValue())) {
                rule = queryTxtField.getValue();
            }

            if (StringUtils.isNotBlank(rule)) {
                fireUpdate(nameTxtField.getValue(), rule);
            }

            editorWindow.close();
        });

        Button cancelBtn = UiUtils.newButton(ICON.CANCLE_CIRCLE);
        cancelBtn.setDescription("Cancel");
        cancelBtn.addClickListener(e -> {
            editorWindow.close();
        });

        HorizontalLayout btnHLayout = new HorizontalLayout();
        btnHLayout.setSpacing(true);
        btnHLayout.addComponent(saveBtn);
        btnHLayout.addComponent(cancelBtn);

        editorForm.addComponent(btnHLayout);
        editorForm.setMargin(true);

        editorWindow.setModal(Boolean.TRUE);
        editorWindow.center();
        editorWindow.setWidth(70, Unit.PERCENTAGE);
        editorWindow.setContent(editorForm);

        return editorWindow;
    }

    private void fireUpdate(String name, String query) {
        eventListenerManager.fireEvent(listener -> listener.save(name, query));
    }

    @Override
    public RuleTableImpl getRuleTable() {
        return queryTable;
    }

    @Override
    public Button getRuleAddButton() {
        return addBtn;
    }

    @Override
    public void addRuleComponentListener(RuleComponentListener queryComponentListener) {
        eventListenerManager.addEventListener(queryComponentListener);

    }

    @Override
    public void setPreparedRules(List<String> preparedRules) {
        this.preparedRules = preparedRules;
    }
}
