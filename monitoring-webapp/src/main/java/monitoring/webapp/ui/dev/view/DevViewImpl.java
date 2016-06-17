package monitoring.webapp.ui.dev.view;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import common.data.Connection;
import common.data.model.DataSource;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.dev.component.DataSourceTable;
import monitoring.webapp.ui.dev.component.DataSourceTableImpl;
import monitoring.webapp.ui.dev.presenter.DevViewPresenter;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.navigator.DevNavigator;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(DevView.VIEW_NAME)
public class DevViewImpl extends AbstractViewImpl<DevNavigator> implements DevView {

    private Label componentId, componentName, componentURL, componentLastUpdated;

    private DataSourceTableImpl dataSourceTable;

    @Override
    protected void init(MonitoringService monitoringService) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void init(VerticalLayout layout) {

        componentId = UiUtils.newLabel(Messages.MESSAGE.COMPONENT_ID);
        componentName = UiUtils.newLabel(Messages.MESSAGE.COMPONENT_NAME);
        componentURL = UiUtils.newLabel(Messages.MESSAGE.COMPONENT_URL);
        componentLastUpdated = UiUtils.newLabel(Messages.MESSAGE.COMPONENT_UPDATED);

        dataSourceTable = new DataSourceTableImpl();
        dataSourceTable.setSelectable(false);
        dataSourceTable.setImmediate(true);
        dataSourceTable.setColumnExpandRatio(DataSourceTable.COLUMN.DOMAIN_INFORMATION, 1.0f);
        dataSourceTable.setWidth(100, Unit.PERCENTAGE);
        dataSourceTable.addStyleName("virtual-table-min-width");

        dataSourceTable.setCaption("Data Source(s)");

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        formLayout.addComponent(componentId);
        formLayout.addComponent(componentName);
        formLayout.addComponent(componentURL);
        formLayout.addComponent(componentLastUpdated);
        formLayout.addComponent(dataSourceTable);

        layout.addComponent(formLayout);
    }

    @Override
    public void setConnection(Connection connection) {

        componentId.setValue(Long.toString(connection.getId()));
        componentName.setValue(connection.getName());
        componentURL.setValue(connection.getUrl().getAuthority());
        componentLastUpdated.setValue(connection.getUpdated().toString());
    }

    @Override
    protected void enter(DevNavigator navigator, MonitoringService monitoringService) {

        final DevViewPresenter devViewPresenter = new DevViewPresenter(monitoringService);
        devViewPresenter.setDevId(Long.parseLong(navigator.getDevId()));
        devViewPresenter.setUserInterface(this);
        devViewPresenter.setModel(monitoringService);
    }

    @Override
    protected DevNavigator createInstance(String parameters) {
        return new DevNavigator(parameters);
    }

    @Override
    public void setDataSources(List<DataSource> dataSources) {
        dataSourceTable.addBeanItems(dataSources);
    }

    @Override
    public DataSourceTable getDataSourcesTable() {
        return dataSourceTable;
    }
}
