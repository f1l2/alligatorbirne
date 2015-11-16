package monitoring.webapp.ui.browser.view;

import org.springframework.context.annotation.Scope;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.browser.presenter.BrowserViewPresenter;
import monitoring.webapp.ui.navigator.BrowserNavigator;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(BrowserView.VIEW_NAME)
public class BrowserViewImpl extends AbstractViewImpl<BrowserNavigator>implements BrowserView {
    private BrowserFrame browser;

    @Override
    protected void init(MonitoringService monitoringService) {

    }

    @Override
    protected void enter(BrowserNavigator navigator, MonitoringService monitoringService) {

        BrowserViewPresenter monitoringViewPresenter = new BrowserViewPresenter(monitoringService);
        monitoringViewPresenter.setUserInterface(this);
        monitoringViewPresenter.setModel(navigator.getBrowserResource());
    }

    @Override
    protected void init(VerticalLayout layout) {

        browser = new BrowserFrame();
        browser.setWidth(100, Unit.PERCENTAGE);
        browser.setHeight(100, Unit.PERCENTAGE);
        layout.addComponent(browser);

        setBrowserLayoutHeight(Page.getCurrent().getBrowserWindowHeight());

        Page.getCurrent().addBrowserWindowResizeListener(event -> {
            setBrowserLayoutHeight(event.getHeight());
        });

    }

    @Override
    public void setUrl(String url) {
        browser.setSource(new ExternalResource(url));
    }

    @Override
    protected BrowserNavigator createInstance(String parameters) {
        return new BrowserNavigator(parameters);
    }

    private void setBrowserLayoutHeight(int height) {
        browser.setHeight(new Double(height - 163).floatValue(), Unit.PIXELS);
    }

}
