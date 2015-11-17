package monitoring.webapp.ui.ep.component;

import common.data.dto.QueryDTO;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface QueryTable extends BeanItemTable<QueryDTO, QueryTable.COLUMN> {

    public enum COLUMN implements BeanItemColumn {
        /**/
        NAME("ID"),
        /**/
        QUERY("QUERY");

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
