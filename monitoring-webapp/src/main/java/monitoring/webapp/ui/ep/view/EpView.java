package monitoring.webapp.ui.ep.view;

import java.util.List;

import common.data.Connection;
import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;
import monitoring.webapp.ui.view.AbstractView;

public interface EpView extends AbstractView {
    public static final String VIEW_NAME = "/ep";

    public void setConnection(Connection connection);

    public void setQueryTable(List<QueryDTO> queries);

    public void setRuleTable(List<RuleDTO> rules);

}
