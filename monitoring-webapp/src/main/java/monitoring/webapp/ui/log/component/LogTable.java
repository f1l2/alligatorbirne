package monitoring.webapp.ui.log.component;

import common.data.dto.LogDTO;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface LogTable extends BeanItemTable<LogDTO, LogTable.COLUMN> {

    public enum COLUMN implements BeanItemColumn {
        /**/
        NAME("Name"),
        /**/
        MESSAGE("Message"),
        /**/
        TIME("TIME");

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
