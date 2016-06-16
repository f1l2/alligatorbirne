package monitoring.webapp.ui.ep.view;

import java.util.EventListener;
import java.util.List;

import common.data.Connection;
import common.data.dto.LogDTO;
import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;
import monitoring.webapp.ui.log.component.LogTable;
import monitoring.webapp.ui.view.AbstractView;

public interface EpView extends AbstractView {
    public static final String VIEW_NAME = "/ep";

    public void setConnection(Connection connection);

    public void setQueryTable(List<QueryDTO> queries);

    public void setRuleTable(List<RuleDTO> rules);

    public LogTable getLogTableTriggeredAction();

    public void setLogTableTriggeredAction(List<LogDTO> logs);

    public interface EPViewListener extends EventListener {
        void refresh();
    }

    public void addEPViewListener(EPViewListener listener);

}
