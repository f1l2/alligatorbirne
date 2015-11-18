package monitoring.webapp.ui.statement.component;

import java.util.EventListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

import monitoring.webapp.ui.ep.component.QueryTableImpl;
import monitoring.webapp.ui.notify.component.Notify;

public interface QueryComponent extends Notify {

    public QueryTableImpl getQueryTable();

    public Button getQueryAddButton();

    public void addQueryComponentListener(QueryComponentListener externalLinkComponentListener);

    public static interface QueryComponentListener extends EventListener {

        public void save(String name, String query);
    }

    public Window getAddQueryWindow();

}
