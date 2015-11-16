package monitoring.webapp.ui.table.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.Table;

import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.notify.component.NotifyManager;
import monitoring.webapp.ui.notify.model.NotifyModel;
import monitoring.webapp.ui.table.model.BeanItemColumn;

@SuppressWarnings("serial")
public abstract class BeanItemTableImpl<BI, BIC extends BeanItemColumn> extends Table implements BeanItemTable<BI, BIC> {

    private EventListenerManager<BeanItemTableValueChangeListener<BI>> changeEventListenerManager = new EventListenerManager<BeanItemTableValueChangeListener<BI>>();

    private EventListenerManager<BeanItemTableSetChangeListener<BI>> setEventListenerManager = new EventListenerManager<BeanItemTableSetChangeListener<BI>>();

    protected EventListenerManager<BeanItemTableCRUDActionListener<BI>> actionEventListenerManager = new EventListenerManager<BeanItemTableCRUDActionListener<BI>>();

    private EventListenerManager<BeanItemTableFooterListener<BI>> footerEventListenerManager = new EventListenerManager<BeanItemTableFooterListener<BI>>();

    public BeanItemTableImpl(Class<BI> beanType) {
        setContainerDataSource(new BeanItemContainer<BI>(beanType) {
            @Override
            protected void fireItemSetChange(com.vaadin.data.Container.ItemSetChangeEvent event) {
                super.fireItemSetChange(event);
                fireSetBeanItems(getVisibleItemIds());
            }
        });
        setVisibleColumns(new Object[0]);
        setPageLength(0);

        addValueChangeListener(new Property.ValueChangeListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                Table table = (Table) event.getProperty();
                if (table.isSelectable()) {

                    List<BI> selectedItems = new ArrayList<BI>();

                    Object value = table.getValue();

                    if (value != null) {
                        if (table.isMultiSelect()) {
                            for (BI selectedItem : (Collection<BI>) value) {
                                selectedItems.add(selectedItem);
                            }
                        } else {
                            selectedItems.add((BI) value);
                        }
                    }

                    fireSelectBeanItems(selectedItems);
                }
            }
        });

    }

    public void addColumn(BIC beanItemColumn, BeanItemColumnGenerator<BI> columnGenerator) {
        setColumnHeader(beanItemColumn, beanItemColumn.getName());
        addGeneratedColumn(beanItemColumn, columnGenerator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BeanItemContainer<BI> getContainerDataSource() {
        return (BeanItemContainer<BI>) super.getContainerDataSource();
    }

    @Override
    public void setVisible(boolean value) {
        super.setVisible(value);
    }

    @Override
    public void setSelectable(boolean selectable) {
        super.setSelectable(selectable);
        checkSelectable();

        if (selectable) {
            addCheckBoxColumn();
        }
    }

    @Override
    public void setMultiSelect(boolean multiSelect) {
        super.setMultiSelect(multiSelect);
        checkSelectable();
    }

    private void checkSelectable() {
        removeStyleName("v-selectable");
        removeStyleName("v-table-focus");
        if (isSelectable()) {
            addStyleName("v-selectable");
            addStyleName("v-table-focus");
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
                newVisibleColumns.add(0, column);
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
    public void setVisibleColumns(@SuppressWarnings("unchecked") BIC... visibleColumns) {
        super.setVisibleColumns((Object[]) visibleColumns);
    }

    @Override
    public void removeBeanItem(BI beanItem) {
        getContainerDataSource().removeItem(beanItem);
        unselect(beanItem);
    }

    @Override
    public void selectBeanItems(Collection<BI> beanItems) {
        for (BI beanItem : beanItems) {
            select(beanItem);
        }
    }

    @Override
    public BI addBeanItem(BI beanItem) {
        getContainerDataSource().addBean(beanItem);
        return beanItem;
    }

    @Override
    public <BIS extends Collection<BI>> BIS addBeanItems(BIS beanItems) {
        getContainerDataSource().addAll(beanItems);
        return beanItems;
    }

    @Override
    public void removeAllBeanItems() {
        setValue(null);
        getContainerDataSource().removeAllItems();
    }

    @Override
    public <BIS extends Collection<BI>> BIS setBeanItems(BIS beanItems) {
        removeAllBeanItems();
        return addBeanItems(beanItems);
    }

    @Override
    public void addBeanItemTableSetChangeListener(BeanItemTableSetChangeListener<BI> beanItemTableSetChangeListener) {
        setEventListenerManager.addEventListener(beanItemTableSetChangeListener);
    }

    @Override
    public void addBeanItemTableValueChangeListener(BeanItemTableValueChangeListener<BI> beanItemTableValueChangeListener) {
        changeEventListenerManager.addEventListener(beanItemTableValueChangeListener);
    }

    @Override
    public void addBeanItemTableCRUDActionListener(BeanItemTableCRUDActionListener<BI> beanItemTableCRUDActionListener) {
        actionEventListenerManager.addEventListener(beanItemTableCRUDActionListener);
    }

    @Override
    public void addBeanItemTableFooterListener(BeanItemTableFooterListener<BI> beanItemTableFooterListener) {
        footerEventListenerManager.addEventListener(beanItemTableFooterListener);
    }

    protected void fireSetBeanItems(List<BI> beanItems) {

        BeanItemTableSetChangeEvent<BI> event = new BeanItemTableSetChangeEvent<BI>(this, beanItems);

        setEventListenerManager.fireEvent(listener -> listener.setChange(event));
    }

    protected void fireSelectBeanItems(List<BI> beanItems) {

        BeanItemTableValueChangeEvent<BI> event = new BeanItemTableValueChangeEvent<BI>(this, beanItems);

        changeEventListenerManager.fireEvent(listener -> listener.valueChange(event));
    }

    protected void fireFooterBeanItems(int totalSize) {

        BeanItemTableFooterEvent<BI> event = new BeanItemTableFooterEvent<BI>(this, totalSize);

        footerEventListenerManager.fireEvent(listener -> listener.update(event));

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addFilter(BeanItemTable.BeanItemTableFilter<BI> beanItemTableFilter) {
        removeFilter(beanItemTableFilter);
        getContainerDataSource().addContainerFilter(new BeanItemTableFilterWrapper(beanItemTableFilter));

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

    private static class BeanItemTableFilterWrapper<BI> implements Filter {

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
    public int getPageLength() {
        int pageLength = super.getPageLength();
        if (pageLength > 0) {
            int size = getContainerDataSource().getItemIds().size();
            if (size < pageLength) {
                pageLength = size;
            }
        }
        return pageLength;
    }

    @Override
    public void removeAllFilter() {
        getContainerDataSource().removeAllContainerFilters();
    }

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        NotifyManager.addNotify(this, notifyModel);
    }

    private void addCheckBoxColumn() {
        final String COLUM_CB = "CHECK_BOX";

        setColumnHeader(COLUM_CB, "");
        addGeneratedColumn(COLUM_CB, new ColumnGenerator() {

            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                return null;
            }
        });

        this.setColumnWidth(COLUM_CB, 24);

        Object[] visibleColumns = getVisibleColumns();
        List<Object> newVisibleColumns = new ArrayList<Object>();

        if (visibleColumns != null) {
            for (Object visibleColumn : visibleColumns) {
                if ((visibleColumn != null) && (!visibleColumn.equals(COLUM_CB))) {
                    newVisibleColumns.add(visibleColumn);
                }
            }
        }

        newVisibleColumns.add(0, COLUM_CB);

        super.setVisibleColumns(newVisibleColumns.toArray());

    }

    @Override
    public void setPageLength(int pageLength) {
        super.setPageLength(pageLength);
        fireFooterBeanItems(this.getContainerDataSource().size());
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        fireFooterBeanItems(this.getContainerDataSource().size());
    }
}
