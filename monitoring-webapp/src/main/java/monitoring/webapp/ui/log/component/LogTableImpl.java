package monitoring.webapp.ui.log.component;

import common.data.dto.LogDTO;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;

@SuppressWarnings("serial")
public class LogTableImpl extends BeanItemTableImpl<LogDTO, LogTable.COLUMN>implements LogTable {

    public LogTableImpl() {

        super(LogDTO.class);

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<LogDTO>() {

            @Override
            public Object generateCell(LogDTO beanItem) {
                return beanItem.getName();
            }

        });

        addColumn(COLUMN.TIME, new BeanItemColumnGenerator<LogDTO>() {
            @Override
            public Object generateCell(LogDTO beanItem) {
                return beanItem.getDate();
            }
        });

        addColumn(COLUMN.MESSAGE, new BeanItemColumnGenerator<LogDTO>() {

            public Object generateCell(LogDTO beanItem) {
                return beanItem.getMessage();
            }
        });
    }
}
