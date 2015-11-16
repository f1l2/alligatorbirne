package monitoring.webapp.ui.table.model;

import java.util.Collection;

public interface HierarchicalBeanItem<BI> {
    BI getParent();
    Collection<BI> getChilds();
}
