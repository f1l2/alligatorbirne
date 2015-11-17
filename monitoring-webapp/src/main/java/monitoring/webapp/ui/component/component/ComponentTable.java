package monitoring.webapp.ui.component.component;

import common.data.Connection;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface ComponentTable extends BeanItemTable<Connection, ComponentTable.COLUMN> {

    public enum SECTION {
        VIEW_SERVICE_INSTANCES
    };

    public enum COLUMN implements BeanItemColumn {
        /**/
        ID("ID"),
        /**/
        URL("URL"),
        /**/
        NAME("NAME"),
        /**/
        ACTION("ACTION");

        private final String name;

        COLUMN(String id) {
            this.name = Messages.getString(id, id);
        }

        @Override
        public String getName() {
            return name;
        }

    }

}
