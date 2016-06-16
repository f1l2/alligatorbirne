package monitoring.webapp.ui.ep.view;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import common.data.Connection;
import common.data.dto.LogDTO;
import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.component.QueryTable;
import monitoring.webapp.ui.ep.component.QueryTableImpl;
import monitoring.webapp.ui.ep.component.RuleTable;
import monitoring.webapp.ui.ep.component.RuleTableImpl;
import monitoring.webapp.ui.ep.presenter.EpViewPresenter;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.i18n.Messages.MESSAGE;
import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.log.component.LogTableImpl;
import monitoring.webapp.ui.navigator.EpNavigator;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(EpView.VIEW_NAME)
public class EpViewImpl extends AbstractViewImpl<EpNavigator> implements EpView {

    private EventListenerManager<EPViewListener> eventListenerManager = new EventListenerManager<EPViewListener>();

    private Label componentId, componentName, componentURL, componentLastUpdated, componentCPUUsage, componentRAMUsage;

    private QueryTableImpl queryTable;

    private RuleTableImpl ruleTable;

    private LogTableImpl logTableTriggeredAction;

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {

        componentId = UiUtils.newLabel(MESSAGE.COMPONENT_ID);
        componentName = UiUtils.newLabel(MESSAGE.COMPONENT_NAME);
        componentURL = UiUtils.newLabel(MESSAGE.COMPONENT_URL);
        componentLastUpdated = UiUtils.newLabel(MESSAGE.COMPONENT_UPDATED);
        componentCPUUsage = UiUtils.newLabel(MESSAGE.CPU_USAGE);
        componentRAMUsage = UiUtils.newLabel(MESSAGE.RAM_USAGE);

        queryTable = new QueryTableImpl();
        queryTable.setSelectable(false);
        queryTable.setImmediate(true);
        queryTable.setColumnExpandRatio(QueryTable.COLUMN.QUERY, 1.0f);
        queryTable.setWidth(100, Unit.PERCENTAGE);
        queryTable.addStyleName("virtual-table-min-width");

        ruleTable = new RuleTableImpl(true);
        ruleTable.setSelectable(false);
        ruleTable.setImmediate(true);
        ruleTable.setColumnExpandRatio(RuleTable.COLUMN.RULE, 1.0f);
        ruleTable.setWidth(100, Unit.PERCENTAGE);
        ruleTable.addStyleName("virtual-table-min-width");

        logTableTriggeredAction = new LogTableImpl();
        logTableTriggeredAction.setSelectable(false);
        logTableTriggeredAction.setImmediate(true);
        logTableTriggeredAction.setColumnExpandRatio(LogTable.COLUMN.MESSAGE, 1.0f);
        logTableTriggeredAction.setWidth(100, Unit.PERCENTAGE);
        logTableTriggeredAction.addStyleName("virtual-table-min-width");

        queryTable.setCaption("Registered Queries");
        ruleTable.setCaption("Registered Rules");
        logTableTriggeredAction.setCaption("Triggered Actions");

        Button refreshButton = UiUtils.newButton(ICON.UPDATE);
        refreshButton.setDescription("Refresh");
        refreshButton.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.refresh());
        });

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.addComponent(refreshButton);

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        formLayout.addComponent(componentId);
        formLayout.addComponent(componentName);
        formLayout.addComponent(componentURL);
        formLayout.addComponent(componentLastUpdated);
        formLayout.addComponent(componentCPUUsage);
        formLayout.addComponent(componentRAMUsage);
        formLayout.addComponent(queryTable);
        formLayout.addComponent(ruleTable);
        formLayout.addComponent(hLayout);
        formLayout.addComponent(logTableTriggeredAction);

        layout.addComponent(formLayout);
    }

    @Override
    public void setConnection(Connection connection) {

        componentId.setValue(Long.toString(connection.getId()));
        componentName.setValue(connection.getName());
        componentURL.setValue(connection.getUrl().getAuthority());
        componentLastUpdated.setValue(connection.getUpdated().toString());
        componentCPUUsage.setValue(connection.getValue1().toString());
        componentRAMUsage.setValue(connection.getValue2().toString());

    }

    @Override
    public void setRuleTable(List<RuleDTO> rules) {
        ruleTable.addBeanItems(rules);
    }

    @Override
    public void setQueryTable(List<QueryDTO> queries) {
        queryTable.addBeanItems(queries);
    }

    @Override
    public void setLogTableTriggeredAction(List<LogDTO> logs) {
        logTableTriggeredAction.addBeanItems(logs);
    }

    @Override
    protected void enter(EpNavigator navigator, MonitoringService monitoringService) {

        final EpViewPresenter epViewPresenter = new EpViewPresenter(monitoringService);
        epViewPresenter.setEpId(Long.parseLong(navigator.getEpId()));
        epViewPresenter.setUserInterface(this);
        epViewPresenter.setModel(monitoringService);

    }

    @Override
    protected EpNavigator createInstance(String parameters) {
        return new EpNavigator(parameters);
    }

    @Override
    public void addEPViewListener(EPViewListener listener) {
        this.eventListenerManager.addEventListener(listener);
    }

    @Override
    public LogTable getLogTableTriggeredAction() {
        return logTableTriggeredAction;
    }
}
