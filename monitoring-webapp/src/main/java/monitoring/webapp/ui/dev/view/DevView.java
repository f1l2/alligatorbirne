package monitoring.webapp.ui.dev.view;

import java.util.List;

import common.data.Connection;
import common.data.model.DataSource;
import monitoring.webapp.ui.view.AbstractView;

public interface DevView extends AbstractView {
    public static final String VIEW_NAME = "/dev";

    public void setConnection(Connection connection);

    public void setDataSources(List<DataSource> listDataSourcesById);

}
