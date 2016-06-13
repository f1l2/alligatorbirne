package monitoring.webapp.ui.statement.component;

import java.util.Arrays;
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

import common.data.dto.RuleDTO;
import common.selection.DISTRIBUTION_STRATEGY;
import monitoring.webapp.ui.ep.component.RuleTable;
import monitoring.webapp.ui.ep.component.RuleTableImpl;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.notify.component.NotifyComponent;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;

@SuppressWarnings("serial")
public class RuleComponentImpl extends NotifyComponent implements RuleComponent {

    private EventListenerManager<RuleComponentListener> eventListenerManager = new EventListenerManager<RuleComponentListener>();

    private RuleTableImpl ruleTable;

    private Button addBtn;

    private ComboBox cbStrategy;

    private List<RuleDTO> preparedRules;

    public RuleComponentImpl() {

        addBtn = UiUtils.newButton(ICON.ADD);
        addBtn.setDescription("Add new external link");
        addBtn.addClickListener(listener -> {
            UI.getCurrent().addWindow(getAddRuleWindow());
        });

        ruleTable = new RuleTableImpl(false);
        ruleTable.setSelectable(false);
        ruleTable.setImmediate(true);
        ruleTable.setColumnExpandRatio(RuleTable.COLUMN.RULE, 1.0f);
        ruleTable.setWidth(100, Unit.PERCENTAGE);
        ruleTable.addStyleName("virtual-table-min-width");

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setMargin(true);
        btnLayout.addComponent(addBtn);
        btnLayout.addStyleName("monitoring-spacing-margin-inner");

        cbStrategy = new ComboBox("Distribution Strategy");
        cbStrategy.addStyleName("monitoring-label-monospace");

        Arrays.stream(DISTRIBUTION_STRATEGY.values()).forEach(ds -> cbStrategy.addItem(ds.getDescription()));

        final FormLayout formLayout = new FormLayout();
        formLayout.addComponent(cbStrategy);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        vLayout.addComponent(ruleTable);
        vLayout.addComponent(btnLayout);
        vLayout.addComponent(formLayout);

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
        preparedRules.stream().forEach(pq -> ruleComboBox.setItemCaption(pq, pq.getRule()));

        ruleComboBox.addValueChangeListener(l -> {
            if (ruleComboBox.getValue() != null) {
                nameTxtField.setValue(((RuleDTO) ruleComboBox.getValue()).getName());
            }
        });

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
                rule = ((RuleDTO) ruleComboBox.getValue()).getRule();
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
        return ruleTable;
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
    public ComboBox getCbStrategy() {
        return cbStrategy;
    }

    @Override
    public void setPreparedRules(List<RuleDTO> preparedRules) {
        this.preparedRules = preparedRules;
    }
}
