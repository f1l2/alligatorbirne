package monitoring.webapp.ui.dev.component;

import common.data.DataSource;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;

@SuppressWarnings("serial")
public class DataSourceTableImpl extends BeanItemTableImpl<DataSource, DataSourceTable.COLUMN>implements DataSourceTable {

    public DataSourceTableImpl() {

        super(DataSource.class);

        addColumn(COLUMN.DEVICE_INFORMATION, new BeanItemColumnGenerator<DataSource>() {

            @Override
            public Object generateCell(DataSource beanItem) {
                return beanItem.getDeviceInformation().getName();
            }

        });

        addColumn(COLUMN.DOMAIN_INFORMATION, new BeanItemColumnGenerator<DataSource>() {

            public Object generateCell(DataSource beanItem) {
                return beanItem.getDomainInformation().getName();
            }
        });
    }
}
