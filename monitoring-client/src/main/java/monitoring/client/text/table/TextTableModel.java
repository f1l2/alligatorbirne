package monitoring.client.text.table;

import javax.swing.table.AbstractTableModel;

public abstract class TextTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -1623729830374997294L;

    public abstract boolean allowNumberingAt(int row);

    public abstract boolean addSeparatorAt(int row);
}
