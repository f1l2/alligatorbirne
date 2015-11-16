package monitoring.webapp.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.ui.breadcrumb.component.Breadcrumb;
import monitoring.webapp.ui.breadcrumb.component.BreadcrumbImpl;
import monitoring.webapp.ui.ep.view.EPView;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.navigator.EPNavigator;
import ru.xpoft.vaadin.DiscoveryNavigator;

@SuppressWarnings("serial")
@Title("Monitoring Webapp")
@Theme("monitoring-theme")
@StyleSheet({ "http://fonts.googleapis.com/css?family=PT+Sans" })
@org.springframework.stereotype.Component("monitoringUI")
@PreserveOnRefresh
public class MonitoringUI extends UI {

    public static final Map<String, Long> SERVICES_MAP = new HashMap<String, Long>();

    private Label lazyLoader;
    private ComponentContainer container;
    private BreadcrumbImpl breadcrumbImpl;

    public Component getLazyLoader() {
        return lazyLoader;
    }

    public MonitoringUI() {
        super();
        VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("monitoring-root-layout");

        layout.addComponent(createTitle());
        layout.addComponent(createMenu());
        layout.addComponent(createBreadcrumb());
        layout.addComponent(createContainer());

        setContent(layout);
    }

    @Override
    protected void init(final VaadinRequest request) {

        setErrorHandler(new MonitoringWebAppErrorHandler());

        new DiscoveryNavigator(this, container) {

            // if no view for state found
            // navigate to ServiceSearch
            @Override
            public void addProvider(ViewProvider provider) {
                super.addProvider(provider);
                View view = provider.getView(EPView.VIEW_NAME);
                if (view != null) {
                    setErrorView(view);
                }
            }
        };

    }

    private Component createTitle() {

        HorizontalLayout layout = new HorizontalLayout();
        layout.addStyleName("monitoring-title-layout");

        final Image logo = new Image();
        logo.setSource(new ThemeResource("img/logo.png"));
        logo.setWidth(58, Unit.PIXELS);
        logo.setHeight(45, Unit.PIXELS);
        layout.addComponent(logo);

        final Label title = new Label("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + Messages.getString("title") + "</h3>", ContentMode.HTML);
        title.setSizeUndefined();
        layout.addComponent(title);

        return layout;
    }

    public Breadcrumb getBreadcrumb() {
        return breadcrumbImpl;
    }

    private BreadcrumbImpl createBreadcrumb() {
        lazyLoader = new Label();
        lazyLoader.addStyleName("monitoring-spinner");
        lazyLoader.addStyleName("monitoring-hide");

        breadcrumbImpl = new BreadcrumbImpl(lazyLoader);
        return breadcrumbImpl;
    }

    private ComponentContainer createContainer() {
        container = new VerticalLayout();
        container.addStyleName("monitoring-content-layout");

        return container;
    }

    private Component createMenu() {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth(100, Unit.PERCENTAGE);
        layout.addStyleName("monitoring-menu-layout");

        MenuBar menuBar = new MenuBar();
        menuBar.setWidth(100, Unit.PERCENTAGE);
        layout.addComponent(menuBar);

        menuBar.addItem("EPs", null, new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                new EPNavigator().navigeteTo();
            }
        });

        menuBar.addItem("DEVs", null, new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                new EPNavigator().navigeteTo();
            }
        });

        menuBar.addItem("abc", null, new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                new EPNavigator().navigeteTo();
            }
        });

        return layout;
    }

}
