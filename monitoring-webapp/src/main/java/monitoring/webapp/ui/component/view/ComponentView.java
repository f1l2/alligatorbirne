package monitoring.webapp.ui.component.view;

import monitoring.webapp.ui.component.component.ComponentTable;
import monitoring.webapp.ui.view.AbstractView;

public interface ComponentView extends AbstractView {
    public static final String VIEW_NAME = "/component";

    public ComponentTable getEPTable();

    public ComponentTable getDevTable();

}
