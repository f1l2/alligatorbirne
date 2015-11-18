
package monitoring.webapp.ui.statement.presenter;

import java.util.List;

import common.codes.SUCCESS_CODES;
import common.data.dto.QueryDTO;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.ep.component.QueryTable.QueryTableListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;
import monitoring.webapp.ui.statement.component.QueryComponent;
import monitoring.webapp.ui.statement.component.QueryComponent.QueryComponentListener;

public class QueryComponentPresenter extends AbstractPresenter<List<QueryDTO>, QueryComponent>implements QueryComponentListener, QueryTableListener {

    public QueryComponentPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    public void save(String name, String query) {

        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setName(name);
        queryDTO.setQuery(query);

        String response = getMonitoringService().registerQuery(name, query);

        if (SUCCESS_CODES.OK.getMessage().equals(response)) {
            getUserInterface().getQueryTable().addBeanItem(queryDTO);
        }

    }

    @Override
    protected void init(List<QueryDTO> model, QueryComponent userInterface) {
        userInterface.getQueryTable().addBeanItems(model);
        userInterface.getQueryTable().addQueryTableListener(this);
    }

    @Override
    protected void initUserInterface(QueryComponent userInterface) {
        userInterface.addQueryComponentListener(this);
    }

    @Override
    protected void initModel(List<QueryDTO> model) {

    }

    @Override
    public void delete(QueryDTO query) {
        getMonitoringService().deregisterQuery(query);
        getUserInterface().getQueryTable().removeAllBeanItems();
        getUserInterface().getQueryTable().addBeanItems(getMonitoringService().listRegisteredQuery());
    }
}
