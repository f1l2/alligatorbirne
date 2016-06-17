package monitoring.webapp.ui.statement.component;

import java.util.EventListener;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

import common.data.dto.QueryDTO;
import monitoring.webapp.ui.ep.component.QueryTableImpl;
import monitoring.webapp.ui.notify.component.Notify;

public interface QueryComponent extends Notify {

    public QueryTableImpl getQueryTable();

    public Button getQueryAddButton();

    public void addQueryComponentListener(QueryComponentListener externalLinkComponentListener);

    public Window getAddQueryWindow();

    public void setPreparedQueries(List<QueryDTO> preparedQueries);

    public static interface QueryComponentListener extends EventListener {

        public void save(String name, String query);

        public void refresh();
    }

}
