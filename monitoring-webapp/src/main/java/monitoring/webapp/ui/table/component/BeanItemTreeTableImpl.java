package monitoring.webapp.ui.table.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.TreeTable;

import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.notify.component.NotifyManager;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.table.model.BeanItemColumn;
import monitoring.webapp.ui.table.model.HierarchicalBeanItem;

@SuppressWarnings("serial")
public abstract class BeanItemTreeTableImpl<BI extends HierarchicalBeanItem<BI>, BIC extends BeanItemColumn> extends TreeTable implements BeanItemTreeTable<BI, BIC> {

    private EventListenerManager<BeanItemTableValueChangeListener<BI>> changeEventListenerManager = new EventListenerManager<BeanItemTableValueChangeListener<BI>>();
    private EventListenerManager<BeanItemTableSetChangeListener<BI>> setEventListenerManager = new EventListenerManager<BeanItemTableSetChangeListener<BI>>();

    public BeanItemTreeTableImpl(Class<BI> beanType) {
        setContainerDataSource(new HierarchicalBeanItemContainer<BI>(beanType) {
            @Override
            protected void fireItemSetChange(com.vaadin.data.Container.ItemSetChangeEvent event) {
                super.fireItemSetChange(event);
                fireSetBeanItems(this.getAllItemIds());
            }
        });
        setVisibleColumns(new Object[0]);
        setPageLength(0);

        addValueChangeListener(new Property.ValueChangeListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Property<?> property = event.getProperty();
                if (property != null) {
                    Object value = property.getValue();

                    if (value != null) {
                        List<BI> beanItems = new ArrayList<BI>();

                        for (Object object : Arrays.asList(value)) {
                            beanItems.add((BI) object);
                        }

                        fireSelectBeanItems(beanItems);
                    } else {
                        fireSelectBeanItems(Collections.emptyList());
                    }
                }
            }
        });
    }

    public void addColumn(BeanItemColumn beanItemColumn, BeanItemColumnGenerator<BI> columnGenerator) {
        setColumnHeader(beanItemColumn, beanItemColumn.getName());
        addGeneratedColumn(beanItemColumn, columnGenerator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HierarchicalBeanItemContainer<BI> getContainerDataSource() {
        return (HierarchicalBeanItemContainer<BI>) super.getContainerDataSource();
    }

    @Override
    public void expand(BI itemId, boolean childs) {
        HierarchicalBeanItemContainer<BI> container = getContainerDataSource();
        Collection<BI> children = container.getChildren(itemId);
        if (children != null && children.size() > 0) {
            setCollapsed(itemId, false);

            if (childs) {
                for (BI child : children) {
                    expand(child, childs);
                }
            }
        }
    }

    @Override
    public int getPageLength() {
        int pageLength = super.getPageLength();
        if (pageLength > 0) {
            int size = size();
            if (size < pageLength) {
                pageLength = size;
            }
        }
        return pageLength;
    }

    @Override
    public void expandRoots() {
        HierarchicalBeanItemContainer<BI> container = getContainerDataSource();
        for (BI rootBeanItem : container.rootItemIds()) {
            expand(rootBeanItem, false);
        }
    }

    @Override
    public void setColumnVisible(BIC column, boolean visible) {
        Object[] visibleColumns = getVisibleColumns();

        List<Object> newVisibleColumns = new ArrayList<Object>();

        if (visibleColumns != null) {
            for (Object visibleColumn : visibleColumns) {
                if (visibleColumn != null) {
                    newVisibleColumns.add(visibleColumn);
                }
            }
        }

        if (visible) {
            if (!newVisibleColumns.contains(column)) {
                newVisibleColumns.add(column);
                super.setVisibleColumns(newVisibleColumns.toArray());
            }
        } else {
            if (newVisibleColumns.contains(column)) {
                newVisibleColumns.remove(column);
                super.setVisibleColumns(newVisibleColumns.toArray());
            }

        }

    }

    @Override
    public void setVisible(boolean value) {
        super.setVisible(value);
    }

    @Override
    public void setVisibleColumns(@SuppressWarnings("unchecked") BIC... visibleColumns) {
        super.setVisibleColumns((Object[]) visibleColumns);
    }

    @Override
    public BI addBeanItem(BI beanItem) {
        getContainerDataSource().addBean(beanItem);
        return beanItem;
    }

    @Override
    public void removeBeanItem(BI beanItem) {
        getContainerDataSource().removeItem(beanItem);
    }

    @Override
    public void selectBeanItems(Collection<BI> beanItems) {
        for (BI beanItem : beanItems) {
            select(beanItem);
        }
    }

    @Override
    public <BIS extends Collection<BI>> BIS addBeanItems(BIS beanItems) {
        getContainerDataSource().addAll(beanItems);
        return beanItems;
    }

    @Override
    public <BIS extends Collection<BI>> BIS setBeanItems(BIS beanItems) {
        removeAllBeanItems();
        return addBeanItems(beanItems);
    }

    @Override
    public void removeAllBeanItems() {
        getContainerDataSource().removeAllItems();
    }

    @Override
    public void addBeanItemTableSetChangeListener(BeanItemTableSetChangeListener<BI> beanItemTableSetChangeListener) {
        setEventListenerManager.addEventListener(beanItemTableSetChangeListener);
    }

    @Override
    public void addBeanItemTableValueChangeListener(BeanItemTableValueChangeListener<BI> beanItemTableValueChangeListener) {
        changeEventListenerManager.addEventListener(beanItemTableValueChangeListener);
    }

    private void fireSetBeanItems(List<BI> beanItems) {

        BeanItemTableSetChangeEvent<BI> event = new BeanItemTableSetChangeEvent<BI>(this, beanItems);

        setEventListenerManager.fireEvent(listener -> listener.setChange(event));
    }

    private void fireSelectBeanItems(List<BI> beanItems) {

        BeanItemTableValueChangeEvent<BI> event = new BeanItemTableValueChangeEvent<BI>(this, beanItems);

        this.changeEventListenerManager.fireEvent(listener -> listener.valueChange(event));
    }

    @Override
    public void removeFilter(BeanItemTableFilter<BI> beanItemTableFilter) {
        Filter removeFilter = null;
        for (Filter filter : getContainerDataSource().getContainerFilters()) {

            if (filter != null && filter instanceof BeanItemTableFilterWrapper) {
                if (((BeanItemTableFilterWrapper<?>) filter).beanItemTableFilter.equals(beanItemTableFilter)) {
                    removeFilter = filter;
                    break;
                }
            }
        }

        if (removeFilter != null) {
            getContainerDataSource().removeContainerFilter(removeFilter);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addFilter(BeanItemTable.BeanItemTableFilter<BI> beanItemTableFilter) {
        removeFilter(beanItemTableFilter);

        HierarchicalBeanItemContainer<BI> container = getContainerDataSource();
        container.addContainerFilter(new BeanItemTableFilterWrapper(beanItemTableFilter));
    }

    private static class BeanItemTableFilterWrapper<BI extends HierarchicalBeanItem<BI>> implements Filter {

        private final BeanItemTable.BeanItemTableFilter<BI> beanItemTableFilter;

        public BeanItemTableFilterWrapper(BeanItemTable.BeanItemTableFilter<BI> beanItemTableFilter) {
            super();
            this.beanItemTableFilter = beanItemTableFilter;

        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
            return beanItemTableFilter.passesFilter((BI) itemId);
        }

        @Override
        public boolean appliesToProperty(Object propertyId) {
            return true;
        }

    }

    @Override
    public void removeAllFilter() {
        getContainerDataSource().removeAllContainerFilters();
    }

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        NotifyManager.addNotify(this, notifyModel);
    }
}
