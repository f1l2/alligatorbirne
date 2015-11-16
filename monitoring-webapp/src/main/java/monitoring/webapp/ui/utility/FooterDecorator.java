package monitoring.webapp.ui.utility;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.ui.table.component.BeanItemTableImpl;
import monitoring.webapp.ui.table.component.BeanItemTable.BeanItemTableFooterEvent;
import monitoring.webapp.ui.table.component.BeanItemTable.BeanItemTableFooterListener;
import monitoring.webapp.ui.table.model.BeanItemColumn;

@SuppressWarnings("serial")
public class FooterDecorator<BI, BIC extends BeanItemColumn> extends CustomComponent implements BeanItemTableFooterListener<BI> {

    private Label lbNumberOfRecords = new Label();

    private ComboBox cbShowRecords = null;

    public FooterDecorator(BeanItemTableImpl<BI, BIC> table) {
        createLayout(table, NUMBER_OF_RECORDS.TEN);
    }

    public FooterDecorator(BeanItemTableImpl<BI, BIC> table, NUMBER_OF_RECORDS numberOfRecords) {
        createLayout(table, numberOfRecords);
    }

    private void createLayout(BeanItemTableImpl<BI, BIC> table, NUMBER_OF_RECORDS numberOfRecords) {
        table.addBeanItemTableFooterListener(this);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(table);
        vLayout.addComponent(createTableFooter(table, numberOfRecords));

        if (table.getWidth() == -1f) {
            vLayout.setSizeUndefined();
        }

        this.setCompositionRoot(vLayout);
    }

    @Override
    public void update(BeanItemTableFooterEvent<BI> event) {

        Integer totalRecords = event.getEvent();

        if (totalRecords == 0) {
            lbNumberOfRecords.setValue("No records.");
        } else {

            int showRecordsPerPage = (int) cbShowRecords.getValue();

            if (totalRecords < showRecordsPerPage) {
                lbNumberOfRecords.setValue(String.format("%s records", event.getEvent()));
            } else {

                /**
                 * Display of current visible records in a table based on server side informations is not correct in all cases. In generally Vaadin retrieves more rows than actual displayed. Scrolling
                 * within this buffer do not require interaction with server and therefore information about visible records will not be updated.
                 * 
                 * Information about visible records should be handled on client side.
                 */

                lbNumberOfRecords.setValue(String.format("%s records", event.getEvent()));

                // int firstVisibleRecord = event.getSource().getFirstVisible() + 1;
                // int lastVisibleRecord = firstVisibleRecord + showRecordsPerPage - 1;

                // lbNumberOfRecords.setValue(String.format("%d - %d / %d", firstVisibleRecord, lastVisibleRecord, totalRecords));
            }
        }
    }

    private HorizontalLayout createTableFooter(BeanItemTableImpl<BI, BIC> table, NUMBER_OF_RECORDS numberOfRecords) {

        lbNumberOfRecords.addStyleName("small");

        final Label lbShowRecords = new Label("Show records");
        lbShowRecords.addStyleName("small");
        lbShowRecords.setWidth(80, Unit.PIXELS);

        cbShowRecords = new ComboBox();
        cbShowRecords.addStyleName("small");
        cbShowRecords.addStyleName("borderless");
        cbShowRecords.setWidth(50, Unit.PIXELS);
        cbShowRecords.setImmediate(true);
        cbShowRecords.setNullSelectionAllowed(false);
        cbShowRecords.addValueChangeListener(event -> {
            int pageLength = Integer.valueOf(String.valueOf(event.getProperty().getValue()));
            table.setPageLength(pageLength);
        });
        for (final NUMBER_OF_RECORDS numberOfRecord : NUMBER_OF_RECORDS.values()) {
            cbShowRecords.addItem(numberOfRecord.value);
        }
        cbShowRecords.select(numberOfRecords.value);

        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.addStyleName("monitoring-border-top-grey");
        hLayout.setWidth(100, Unit.PERCENTAGE);
        hLayout.setSpacing(true);
        hLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        hLayout.addComponent(lbNumberOfRecords);
        hLayout.addComponent(lbShowRecords);
        hLayout.addComponent(cbShowRecords);

        hLayout.setExpandRatio(lbNumberOfRecords, 1.0f);
        hLayout.addStyleName("monitoring-padding-left-small");
        hLayout.addStyleName("monitoring-padding-right-small");

        return hLayout;
    }

    public static enum NUMBER_OF_RECORDS {
        FIVE(5),

        TEN(10),

        FIFTEEN(15),

        TWENTY(20),

        TWENTY_FIVE(25);

        private int value;

        NUMBER_OF_RECORDS(int value) {
            this.value = value;
        }
    }

}
