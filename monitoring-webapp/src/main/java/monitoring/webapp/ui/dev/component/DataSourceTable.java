package monitoring.webapp.ui.dev.component;

import java.util.EventListener;

import common.data.model.DataSource;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface DataSourceTable extends BeanItemTable<DataSource, DataSourceTable.COLUMN> {

    public enum COLUMN implements BeanItemColumn {
        /**/
        DEVICE_INFORMATION("Device"),
        /**/
        DOMAIN_INFORMATION("Domain"),
        /**/
        ACTION("Action");

        private final String name;

        COLUMN(String id) {
            this.name = Messages.getString(id, id);
        }

        @Override
        public String getName() {
            return name;
        }

    }

    public interface DataSourceTableListener extends EventListener {

        void simulate(String device, String domain, String value);
    }

    public void addListener(DataSourceTableListener listener);

}
