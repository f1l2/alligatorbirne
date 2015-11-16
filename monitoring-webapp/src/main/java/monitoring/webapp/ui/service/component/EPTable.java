package monitoring.webapp.ui.service.component;

import common.data.Connection;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface EPTable extends BeanItemTable<Connection, EPTable.COLUMN> {

    public enum SECTION {
        VIEW_SERVICE_INSTANCES
    };

    public enum COLUMN implements BeanItemColumn {
        /**/
        URL("URL"),
        /**/
        NAME("NAME"),
        /**/
        ACTION("");

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
