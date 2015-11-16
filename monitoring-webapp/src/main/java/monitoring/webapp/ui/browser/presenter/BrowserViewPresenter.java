package monitoring.webapp.ui.browser.presenter;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.browser.model.BrowserResource;
import monitoring.webapp.ui.browser.view.BrowserView;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class BrowserViewPresenter extends AbstractPresenter<BrowserResource, BrowserView> {

    public BrowserViewPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(BrowserResource model, BrowserView userInterface) {
        userInterface.setUrl(model.getUri());
        userInterface.initBreadcrumb().add(model.getName());
    }

    @Override
    protected void initUserInterface(BrowserView userInterface) {

    }

    @Override
    protected void initModel(BrowserResource model) {

    }

}
