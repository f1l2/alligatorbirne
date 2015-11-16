package monitoring.webapp.ui.service.view;

import monitoring.webapp.ui.service.component.EPTable;
import monitoring.webapp.ui.view.AbstractView;

public interface ServiceSearchView extends AbstractView {
    public static final String VIEW_NAME = "/ep";

    public EPTable getServiceTable();

}
