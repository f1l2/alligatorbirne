package monitoring.webapp.ui.ep.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import common.data.dto.RuleDTO;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;

@SuppressWarnings("serial")
public class RuleTableImpl extends BeanItemTableImpl<RuleDTO, RuleTable.COLUMN>implements RuleTable {

    private EventListenerManager<RuleTableListener> eventListenerManager = new EventListenerManager<RuleTableListener>();

    public RuleTableImpl() {

        super(RuleDTO.class);

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<RuleDTO>() {

            @Override
            public Object generateCell(RuleDTO beanItem) {
                return beanItem.getName();
            }

        });

        addColumn(COLUMN.RULE, new BeanItemColumnGenerator<RuleDTO>() {
            @Override
            public Object generateCell(RuleDTO beanItem) {
                return beanItem.getRule();

            }
        });

        addColumn(COLUMN.ACTIVE, new BeanItemColumnGenerator<RuleDTO>() {

            @Override
            public Object generateCell(RuleDTO beanItem) {

                if (beanItem.getIsActive()) {
                    return "Yes";
                }
                return "No";

            }

        });

        addColumn(COLUMN.ACTION, new BeanItemColumnGenerator<RuleDTO>() {
            @Override
            public Object generateCell(RuleDTO beanItem) {

                MenuBar.Command cmdDeActivate = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {
                        if (beanItem.getIsActive()) {
                            eventListenerManager.fireEvent(item -> item.deactivate(beanItem));
                        } else {
                            eventListenerManager.fireEvent(item -> item.activate(beanItem));
                        }
                    }
                };
                MenuBar.Command cmdDelete = new MenuBar.Command() {

                    public void menuSelected(MenuItem selectedItem) {
                        eventListenerManager.fireEvent(item -> item.delete(beanItem));
                    }
                };

                MenuBar menuBar = UiUtils.newMenuBar(STYLE.VISIBLE_HOVER);
                MenuItem topItem = UiUtils.newMenuItem(menuBar, "", null, ICON.CONFIGURATION);

                if (beanItem.getIsActive()) {
                    UiUtils.newMenuItem(topItem, "Deactivate", cmdDeActivate, ICON.UPDATE);
                } else {
                    UiUtils.newMenuItem(topItem, "Activate", cmdDeActivate, ICON.UPDATE);
                    UiUtils.newMenuItem(topItem, "Delete", cmdDelete, ICON.REMOVE);
                }

                VerticalLayout layout = new VerticalLayout();
                layout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
                layout.addComponent(menuBar);

                return layout;
            }
        });
    }

    @Override
    public void addRuleTableListener(RuleTableListener listener) {
        eventListenerManager.addEventListener(listener);
    }

}
