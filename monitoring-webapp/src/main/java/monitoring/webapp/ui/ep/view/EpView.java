package monitoring.webapp.ui.ep.view;

import common.data.Connection;
import monitoring.webapp.ui.view.AbstractView;

public interface EpView extends AbstractView {
    public static final String VIEW_NAME = "/ep";

    public void setConnection(Connection connection);

}
