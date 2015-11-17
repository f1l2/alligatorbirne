package monitoring.webapp.ui.component.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import monitoring.webapp.ui.navigator.DevNavigator;
import monitoring.webapp.ui.navigator.EpNavigator;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;

@SuppressWarnings("serial")
public class ComponentTableImpl extends BeanItemTableImpl<Connection, ComponentTable.COLUMN>implements ComponentTable {

    public ComponentTableImpl() {

        super(Connection.class);

        addColumn(COLUMN.ID, new BeanItemColumnGenerator<Connection>() {

            @Override
            public Object generateCell(Connection beanItem) {
                return beanItem.getId();
            }

        });

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<Connection>() {
            @Override
            public Object generateCell(Connection beanItem) {
                return beanItem.getName();

            }
        });

        addColumn(COLUMN.URL, new BeanItemColumnGenerator<Connection>() {

            @Override
            public Object generateCell(Connection beanItem) {

                if (beanItem.getUrl() != null) {

                    String authority = beanItem.getUrl().getAuthority();

                    if (COMPONENT_TYPE.DEVICE.equals(beanItem.getComponentType())) {
                        return new DevNavigator(Long.toString(beanItem.getId())).createLink(authority, authority);
                    } else if (COMPONENT_TYPE.EVENT_PROCESSING.equals(beanItem.getComponentType())) {
                        return new EpNavigator(Long.toString(beanItem.getId())).createLink(authority, authority);
                    } else {
                        return authority;
                    }
                }
                return null;
            }

        });

        addColumn(COLUMN.ACTION, new BeanItemColumnGenerator<Connection>() {
            @Override
            public Object generateCell(Connection containerBeanType) {

                MenuBar.Command cmdView = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {

                    }
                };
                MenuBar.Command cmdDownload = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {

                    }
                };

                MenuBar menuBar = UiUtils.newMenuBar(STYLE.VISIBLE_HOVER);
                MenuItem topItem = UiUtils.newMenuItem(menuBar, "", null, ICON.CONFIGURATION);

                UiUtils.newMenuItem(topItem, "Service Instances", cmdView, ICON.EYE);
                UiUtils.newMenuItem(topItem, "Download Report", cmdDownload, ICON.FILE_PDF);

                VerticalLayout layout = new VerticalLayout();
                layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
                layout.addComponent(menuBar);

                return layout;
            }
        });
    }

}
