package monitoring.webapp.ui.ep.view;

import monitoring.webapp.ui.ep.component.EPTable;
import monitoring.webapp.ui.view.AbstractView;

public interface EPView extends AbstractView {
    public static final String VIEW_NAME = "/ep";

    public EPTable getServiceTable();

}
