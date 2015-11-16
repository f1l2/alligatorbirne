package monitoring.webapp.ui.table.component;

import java.util.Collection;
import java.util.EventListener;

import monitoring.webapp.ui.event.AbstractEvent;
import monitoring.webapp.ui.notify.component.Notify;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface BeanItemTable<BI, BIC extends BeanItemColumn> extends Notify {

    public <BIS extends Collection<BI>> BIS addBeanItems(BIS beanItems);

    public <BIS extends Collection<BI>> BIS setBeanItems(BIS beanItems);

    public BI addBeanItem(BI beanItem);

    public void selectBeanItems(Collection<BI> beanItems);

    public void removeBeanItem(BI beanItem);

    public void removeAllBeanItems();

    public void setColumnVisible(BIC column, boolean visible);

    public void setVisibleColumns(@SuppressWarnings("unchecked") BIC... visibleColumns);

    public void setVisible(boolean value);

    public static interface BeanItemTableFilter<BI> {
        public boolean passesFilter(BI beanItem);
    }

    public void removeFilter(BeanItemTableFilter<BI> beanItemTableFilter);

    public void addFilter(BeanItemTableFilter<BI> beanItemTableFilter);

    public void removeAllFilter();

    public void addBeanItemTableValueChangeListener(BeanItemTableValueChangeListener<BI> beanItemTableValueChangeListener);

    public void addBeanItemTableSetChangeListener(BeanItemTableSetChangeListener<BI> beanItemTableSetChangeListener);

    public void addBeanItemTableCRUDActionListener(BeanItemTableCRUDActionListener<BI> beanItemTableCRUDActionListener);

    public void addBeanItemTableFooterListener(BeanItemTableFooterListener<BI> beanItemTableFooterListener);

    public interface BeanItemTableValueChangeListener<BI> extends EventListener {
        public void valueChange(BeanItemTableValueChangeEvent<BI> event);
    }

    public interface BeanItemTableSetChangeListener<BI> extends EventListener {
        public void setChange(BeanItemTableSetChangeEvent<BI> event);
    }

    public interface BeanItemTableCRUDActionListener<BI> extends EventListener {

        public void createItem(BI item);

        public void readItem(BI item);

        public void deleteItem(BI item);

        public void updateItem(BI item);

    }

    public interface BeanItemTableFooterListener<BI> extends EventListener {
        public void update(BeanItemTableFooterEvent<BI> event);
    }

    @SuppressWarnings("serial")
    public class BeanItemTableValueChangeEvent<BI> extends AbstractEvent<BeanItemTable<BI, ?>, Collection<BI>> {

        public BeanItemTableValueChangeEvent(BeanItemTable<BI, ?> source, Collection<BI> event) {
            super(source, event);

        }

        public BI getFirstBeanItem() {
            BI beanItem = null;
            if (getEvent().size() > 0) {
                beanItem = getEvent().iterator().next();
            }
            return beanItem;
        }

    }

    @SuppressWarnings("serial")
    public class BeanItemTableSetChangeEvent<BI> extends AbstractEvent<BeanItemTable<BI, ?>, Collection<BI>> {

        public BeanItemTableSetChangeEvent(BeanItemTable<BI, ?> source, Collection<BI> event) {
            super(source, event);

        }

        public BI getFirstBeanItem() {
            BI beanItem = null;
            if (getEvent().size() > 0) {
                beanItem = getEvent().iterator().next();
            }
            return beanItem;
        }

    }

    @SuppressWarnings("serial")
    public class BeanItemTableFooterEvent<BI> extends AbstractEvent<BeanItemTable<BI, ?>, Integer> {

        public BeanItemTableFooterEvent(BeanItemTable<BI, ?> source, Integer event) {
            super(source, event);

        }
    }
}
