package monitoring.webapp.ui.log.view;

import java.util.EventListener;

import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.view.AbstractView;

public interface LogView extends AbstractView {
    public static final String VIEW_NAME = "/log";

    public LogTable getLogTable();

    public interface LogViewListener extends EventListener {
        void refresh();

        void delete();

        void filterEP();

        void filterCM();

        void filterDEV();
    }

    public void addLogViewListener(LogViewListener listener);
}
