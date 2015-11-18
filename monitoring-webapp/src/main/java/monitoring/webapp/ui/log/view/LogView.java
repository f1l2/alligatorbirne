package monitoring.webapp.ui.log.view;

import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.view.AbstractView;

public interface LogView extends AbstractView {
    public static final String VIEW_NAME = "/log";

    public LogTable getLogTable();

}
