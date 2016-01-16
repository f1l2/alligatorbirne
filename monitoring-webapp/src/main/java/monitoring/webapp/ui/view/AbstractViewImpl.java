package monitoring.webapp.ui.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.MonitoringUI;
import monitoring.webapp.ui.breadcrumb.Breadcrumb;
import monitoring.webapp.ui.navigator.AbstractNavigator;
import monitoring.webapp.ui.notify.component.NotifyManager;
import monitoring.webapp.ui.notify.model.NotifyModel;

@SuppressWarnings("serial")
public abstract class AbstractViewImpl<NT extends AbstractNavigator> extends CustomComponent implements AbstractView {

    @Autowired
    private MonitoringService monitoringService;

    @PostConstruct
    private void init() {

        VerticalLayout layout = new VerticalLayout();

        init(layout);

        init(monitoringService);

        setCompositionRoot(layout);

    }

    protected abstract void init(MonitoringService monitoringService);

    protected abstract void init(VerticalLayout layout);

    protected abstract void enter(NT navigator, MonitoringService monitoringService);

    protected abstract NT createInstance(String parameters);

    private NT navigator = null;

    protected NT getNavigator() {
        return navigator;
    }

    @Override
    public final void enter(final ViewChangeEvent event) {
        navigator = createInstance(event.getParameters());
        for (Window window : UI.getCurrent().getWindows()) {
            window.close();
        }
        enter(navigator, monitoringService);
    }

    @Override
    public Breadcrumb initBreadcrumb() {
        return ((MonitoringUI) UI.getCurrent()).getBreadcrumb().init();
    }

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        NotifyManager.addNotify(this, notifyModel);

    }

}
