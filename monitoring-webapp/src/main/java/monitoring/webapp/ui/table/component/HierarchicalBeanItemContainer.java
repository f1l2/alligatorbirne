package monitoring.webapp.ui.table.component;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.Container.Ordered;
import com.vaadin.data.util.BeanItemContainer;

import monitoring.webapp.ui.table.model.HierarchicalBeanItem;

@SuppressWarnings("serial")
public class HierarchicalBeanItemContainer<BI extends HierarchicalBeanItem<BI>> extends BeanItemContainer<BI> implements Hierarchical, Ordered {

    public HierarchicalBeanItemContainer(Class<BI> type) throws IllegalArgumentException {
        super(type);

    }

    @Override
    public Collection<BI> getChildren(Object itemId) {
        Collection<BI> children = new ArrayList<BI>();

        for (BI beanType : getVisibleItemIds()) {
            if (itemId.equals(beanType.getParent())) {
                children.add(beanType);
            }
        }

        return (children.size() == 0) ? null : children;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BI getParent(Object itemId) {
        return ((BI) itemId).getParent();
    }

    @Override
    public Collection<BI> rootItemIds() {
        Collection<BI> rootItemIds = new ArrayList<BI>();
        for (BI beanType : getVisibleItemIds()) {
            if (beanType.getParent() == null) {
                rootItemIds.add(beanType);
            }
        }
        return rootItemIds;
    }

    @Override
    public boolean setParent(Object itemId, Object newParentId) throws UnsupportedOperationException {
        return false;
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {

        return hasChildren(itemId);
    }

    @Override
    public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed) throws UnsupportedOperationException {

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isRoot(Object itemId) {
        return ((BI) itemId).getParent() == null;
    }

    @Override
    public boolean hasChildren(Object itemId) {
        return getChildren(itemId) != null;
    }

}
