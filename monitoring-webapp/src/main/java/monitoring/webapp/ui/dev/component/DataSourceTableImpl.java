package monitoring.webapp.ui.dev.component;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import common.data.model.DataSource;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;

@SuppressWarnings("serial")
public class DataSourceTableImpl extends BeanItemTableImpl<DataSource, DataSourceTable.COLUMN> implements DataSourceTable {

    private EventListenerManager<DataSourceTableListener> eventListenerManager = new EventListenerManager<DataSourceTableListener>();

    public DataSourceTableImpl() {

        super(DataSource.class);

        addColumn(COLUMN.DEVICE_INFORMATION, new BeanItemColumnGenerator<DataSource>() {

            @Override
            public Object generateCell(DataSource beanItem) {
                return beanItem.getDeviceInformation().getName();
            }

        });

        addColumn(COLUMN.DOMAIN_INFORMATION, new BeanItemColumnGenerator<DataSource>() {

            public Object generateCell(DataSource beanItem) {
                return beanItem.getDomainInformation().getName();
            }
        });

        addColumn(COLUMN.ACTION, new BeanItemColumnGenerator<DataSource>() {
            @Override
            public Object generateCell(DataSource beanItem) {

                MenuBar.Command cmdSimulate = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {
                        UI.getCurrent().addWindow(getSimulateWindow(beanItem));
                    }
                };

                MenuBar menuBar = UiUtils.newMenuBar(STYLE.VISIBLE_HOVER);
                MenuItem topItem = UiUtils.newMenuItem(menuBar, "", null, ICON.CONFIGURATION);

                UiUtils.newMenuItem(topItem, "Simulate", cmdSimulate, ICON.EYE);

                VerticalLayout layout = new VerticalLayout();
                layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
                layout.addComponent(menuBar);

                return layout;
            }
        });

    }

    private Window getSimulateWindow(DataSource beanItem) {

        Window editorWindow = new Window();

        FormLayout editorForm = new FormLayout();

        TextField deviceInfoTxtField = new TextField("Device Information");
        TextField domainInfoTxtField = new TextField("Domain Information");
        TextField valueTxtField = new TextField("Sensor value");

        deviceInfoTxtField.setWidth(100, Unit.PERCENTAGE);
        deviceInfoTxtField.setValue(beanItem.getDeviceInformation() != null ? beanItem.getDeviceInformation().getName() : StringUtils.EMPTY);
        domainInfoTxtField.setWidth(100, Unit.PERCENTAGE);
        domainInfoTxtField.setValue(beanItem.getDomainInformation() != null ? beanItem.getDomainInformation().getName() : StringUtils.EMPTY);
        valueTxtField.setWidth(100, Unit.PERCENTAGE);

        editorForm.addComponent(deviceInfoTxtField);
        editorForm.addComponent(domainInfoTxtField);
        editorForm.addComponent(valueTxtField);

        Button saveBtn = UiUtils.newButton(ICON.DISK);
        saveBtn.setDescription("Simulate");
        saveBtn.addClickListener(e -> {
            fireUpdate(deviceInfoTxtField.getValue(), domainInfoTxtField.getValue(), valueTxtField.getValue());
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

    private void fireUpdate(String device, String domain, String value) {
        eventListenerManager.fireEvent(i -> i.simulate(device, domain, value));
    }

    @Override
    public void addListener(DataSourceTableListener listener) {
        eventListenerManager.addEventListener(listener);
    }

}
