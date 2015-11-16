package monitoring.webapp.ui.ep.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import common.data.Connection;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;

@SuppressWarnings("serial")
public class EPTableImpl extends BeanItemTableImpl<Connection, EPTable.COLUMN>implements EPTable {

    public EPTableImpl() {

        super(Connection.class);

        addColumn(COLUMN.URL, new BeanItemColumnGenerator<Connection>() {

            @Override
            public Object generateCell(Connection beanItem) {
                return beanItem.getUrl().getAuthority();
            }

        });

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<Connection>() {
            @Override
            public Object generateCell(Connection beanItem) {
                return beanItem.getUrl().getAuthority();

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
