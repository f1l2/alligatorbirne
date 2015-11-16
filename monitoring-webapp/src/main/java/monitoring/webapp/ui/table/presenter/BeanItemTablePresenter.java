package monitoring.webapp.ui.table.presenter;

import java.util.Collection;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.presenter.AbstractPresenter;
import monitoring.webapp.ui.table.component.BeanItemTable;

public abstract class BeanItemTablePresenter<BI, BIT extends BeanItemTable<?, ?>> extends AbstractPresenter<Collection<BI>, BIT> {

    public BeanItemTablePresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

}
