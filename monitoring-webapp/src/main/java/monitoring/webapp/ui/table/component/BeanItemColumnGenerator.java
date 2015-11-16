package monitoring.webapp.ui.table.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public abstract class BeanItemColumnGenerator<BI> implements ColumnGenerator {

    private static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    @SuppressWarnings("unchecked")
    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Object value = null;
        if (itemId != null) {
            value = generateCell((BI) itemId);

        }
        return value;
    }

    public abstract Object generateCell(BI beanItem);

    protected String convert(Date date) {
        String value = null;

        if (date != null) {
            value = LONG_DATE_FORMAT.format(date);
        }

        return value;
    }

}
