package monitoring.webapp.ui.ep.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import common.data.dto.QueryDTO;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;

@SuppressWarnings("serial")
public class QueryTableImpl extends BeanItemTableImpl<QueryDTO, QueryTable.COLUMN>implements QueryTable {

    private EventListenerManager<QueryTableListener> eventListenerManager = new EventListenerManager<QueryTableListener>();

    public QueryTableImpl() {

        super(QueryDTO.class);

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<QueryDTO>() {

            @Override
            public Object generateCell(QueryDTO beanItem) {
                return beanItem.getName();
            }

        });

        addColumn(COLUMN.QUERY, new BeanItemColumnGenerator<QueryDTO>() {

            public Object generateCell(QueryDTO beanItem) {
                return beanItem.getQuery();
            }
        });

        addColumn(COLUMN.ACTION, new BeanItemColumnGenerator<QueryDTO>() {
            @Override
            public Object generateCell(QueryDTO beanItem) {

                MenuBar.Command cmdDelete = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {
                        eventListenerManager.fireEvent(item -> item.delete(beanItem));
                    }
                };

                MenuBar menuBar = UiUtils.newMenuBar(STYLE.VISIBLE_HOVER);
                MenuItem topItem = UiUtils.newMenuItem(menuBar, "", null, ICON.CONFIGURATION);

                UiUtils.newMenuItem(topItem, "Delete", cmdDelete, ICON.REMOVE);

                VerticalLayout layout = new VerticalLayout();
                layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
                layout.addComponent(menuBar);

                return layout;
            }
        });
    }

    @Override
    public void addQueryTableListener(QueryTableListener listener) {
        eventListenerManager.addEventListener(listener);
    }
}
