package monitoring.webapp.ui.statement.component;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import common.data.dto.QueryDTO;
import monitoring.webapp.ui.ep.component.QueryTable;
import monitoring.webapp.ui.ep.component.QueryTableImpl;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.notify.component.NotifyComponent;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;

@SuppressWarnings("serial")
public class QueryComponentImpl extends NotifyComponent implements QueryComponent {

    private EventListenerManager<QueryComponentListener> eventListenerManager = new EventListenerManager<QueryComponentListener>();

    private QueryTableImpl queryTable;

    private Button addBtn, refreshBtn;

    private List<QueryDTO> preparedQueries;

    public QueryComponentImpl() {

        addBtn = UiUtils.newButton(ICON.ADD);
        addBtn.setDescription("Add new external link");
        addBtn.addClickListener(listener -> {
            UI.getCurrent().addWindow(getAddQueryWindow());
        });

        refreshBtn = UiUtils.newButton(ICON.UPDATE);
        refreshBtn.setDescription("Refresh");
        refreshBtn.addClickListener(item -> {
            eventListenerManager.fireEvent(i -> i.refresh());
        });

        queryTable = new QueryTableImpl();
        queryTable.setWidth(100, Unit.PERCENTAGE);
        queryTable.setSelectable(false);
        queryTable.setImmediate(true);
        queryTable.setColumnExpandRatio(QueryTable.COLUMN.QUERY, 1.0f);
        queryTable.setWidth(100, Unit.PERCENTAGE);
        queryTable.addStyleName("virtual-table-min-width");

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setSpacing(true);
        btnLayout.addComponent(addBtn);
        btnLayout.addComponent(refreshBtn);
        btnLayout.addStyleName("monitoring-spacing-margin-inner");

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setSpacing(true);
        vLayout.addComponent(queryTable);
        vLayout.addComponent(btnLayout);

        setCompositionRoot(vLayout);
    }

    @Override
    public Window getAddQueryWindow() {
        Window editorWindow = new Window();

        FormLayout editorForm = new FormLayout();

        TextField nameTxtField = new TextField("Name");
        TextField queryTxtField = new TextField("Query");
        ComboBox queryComboBox = new ComboBox("");

        queryComboBox.addValueChangeListener(l -> {
            if (queryComboBox.getValue() != null) {
                nameTxtField.setValue(((QueryDTO) queryComboBox.getValue()).getName());
            }
        });

        preparedQueries.stream().forEach(pq -> queryComboBox.addItem(pq));
        preparedQueries.stream().forEach(pq -> queryComboBox.setItemCaption(pq, pq.getQuery()));

        nameTxtField.setWidth(100, Unit.PERCENTAGE);
        queryComboBox.setWidth(100, Unit.PERCENTAGE);
        queryTxtField.setWidth(100, Unit.PERCENTAGE);

        editorForm.addComponent(nameTxtField);
        editorForm.addComponent(queryTxtField);
        editorForm.addComponent(queryComboBox);

        Button saveBtn = UiUtils.newButton(ICON.DISK);
        saveBtn.setDescription("Save");
        saveBtn.addClickListener(e -> {

            String query = null;
            if ((null == queryComboBox.getValue()) && (StringUtils.isBlank(queryTxtField.getValue()))) {
                // do nothing
            } else if (null != queryComboBox.getValue()) {
                query = ((QueryDTO) queryComboBox.getValue()).getQuery();
            } else if (StringUtils.isNotBlank(queryTxtField.getValue())) {
                query = queryTxtField.getValue();
            }

            if (StringUtils.isNotBlank(query)) {
                fireUpdate(nameTxtField.getValue(), query);
            }
            editorWindow.close();
        });

        Button cancelBtn = UiUtils.newButton(ICON.CANCLE_CIRCLE);
        cancelBtn.setDescription("Cancel");
        cancelBtn.addClickListener(e -> {
            editorWindow.close();
        });

        HorizontalLayout btnHLayout = new HorizontalLayout();
        btnHLayout.setSpacing(true);
        btnHLayout.addComponent(saveBtn);
        btnHLayout.addComponent(cancelBtn);

        editorForm.addComponent(btnHLayout);
        editorForm.setMargin(true);

        editorWindow.setModal(Boolean.TRUE);
        editorWindow.center();
        editorWindow.setWidth(70, Unit.PERCENTAGE);
        editorWindow.setContent(editorForm);

        return editorWindow;
    }

    private void fireUpdate(String name, String query) {
        eventListenerManager.fireEvent(listener -> listener.save(name, query));
    }

    @Override
    public QueryTableImpl getQueryTable() {
        return queryTable;
    }

    @Override
    public Button getQueryAddButton() {
        return addBtn;
    }

    @Override
    public void addQueryComponentListener(QueryComponentListener queryComponentListener) {
        eventListenerManager.addEventListener(queryComponentListener);
    }

    @Override
    public void setPreparedQueries(List<QueryDTO> preparedQueries) {
        this.preparedQueries = preparedQueries;
    }

}
