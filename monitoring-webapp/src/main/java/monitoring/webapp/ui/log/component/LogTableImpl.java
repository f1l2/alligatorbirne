package monitoring.webapp.ui.log.component;

import java.text.SimpleDateFormat;

import com.vaadin.ui.Label;

import common.data.dto.LogDTO;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;

@SuppressWarnings("serial")
public class LogTableImpl extends BeanItemTableImpl<LogDTO, LogTable.COLUMN> implements LogTable {

    public LogTableImpl() {

        super(LogDTO.class);

        addColumn(COLUMN.TIME, new BeanItemColumnGenerator<LogDTO>() {
            @Override
            public Object generateCell(LogDTO beanItem) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm:ss");

                return sdf.format(beanItem.getDate());
            }
        });

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<LogDTO>() {

            @Override
            public Object generateCell(LogDTO beanItem) {

                if (beanItem.getName().startsWith("iot.device")) {
                    Label label = new Label("DEVICE");
                    label.addStyleName("monitoring-background-dev");
                    return label;
                }
                if (beanItem.getName().startsWith("configuration.management")) {
                    Label label = new Label("CM");
                    label.addStyleName("monitoring-background-cm");
                    return label;
                }
                if (beanItem.getName().startsWith("event.processing")) {
                    Label label = new Label("EP");
                    label.addStyleName("monitoring-background-ep");
                    return label;
                }

                return beanItem.getName();
            }

        });

        addColumn(COLUMN.MESSAGE, new BeanItemColumnGenerator<LogDTO>() {

            public Object generateCell(LogDTO beanItem) {
                return beanItem.getMessage();
            }
        });
    }
}
