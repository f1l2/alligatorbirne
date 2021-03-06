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

import monitoring.webapp.ui.breadcrumb.Breadcrumb;
import monitoring.webapp.ui.breadcrumb.BreadcrumbImpl;
import monitoring.webapp.ui.component.view.ComponentView;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.navigator.ComponentNavigator;
import monitoring.webapp.ui.navigator.LogNavigator;
import monitoring.webapp.ui.navigator.MessagingNavigator;
import monitoring.webapp.ui.navigator.StatementNavigator;
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
                View view = provider.getView(ComponentView.VIEW_NAME);
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
        logo.setWidth(45, Unit.PIXELS);
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

        menuBar.addItem("Components", null, new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                new ComponentNavigator().navigeteTo();
            }
        });

        menuBar.addItem("Rule / Query", null, new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                new StatementNavigator().navigeteTo();
            }
        });

        menuBar.addItem("Messaging", null, new MenuBar.Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                new MessagingNavigator().navigeteTo();

            }
        });

        menuBar.addItem("Log", null, new MenuBar.Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                new LogNavigator().navigeteTo();

            }
        });

        return layout;
    }

}
