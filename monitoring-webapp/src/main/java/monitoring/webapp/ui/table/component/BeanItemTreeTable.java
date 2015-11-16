package monitoring.webapp.ui.table.component;

import monitoring.webapp.ui.table.model.BeanItemColumn;
import monitoring.webapp.ui.table.model.HierarchicalBeanItem;

public interface BeanItemTreeTable<BI extends HierarchicalBeanItem<BI>, BIC extends BeanItemColumn> extends BeanItemTable<BI, BIC> {

    public void expand(BI beanItem, boolean childs);

    public void expandRoots();
}
