package monitoring.webapp.ui.breadcrumb;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class BreadcrumbImpl extends CustomComponent implements Breadcrumb {

    private Label envLabel;

    private HorizontalLayout breadcrumpItemLayout;

    public BreadcrumbImpl(Component lazyLoader) {

        HorizontalLayout layout = new HorizontalLayout();
        layout = new HorizontalLayout();
        layout.setStyleName("monitoring-breadcrumb");
        layout.setWidth(100, Unit.PERCENTAGE);

        breadcrumpItemLayout = new HorizontalLayout();
        layout.addComponent(breadcrumpItemLayout);

        envLabel = new Label();
        envLabel.setStyleName("monitoring-breadcrumb-env");
        layout.addComponent(envLabel);

        lazyLoader.addStyleName("monitoring-breadcrumb-lazyLoader");
        layout.addComponent(lazyLoader);
        layout.setExpandRatio(lazyLoader, 1);

        setCompositionRoot(layout);
    }

    @Override
    public Breadcrumb init() {

        breadcrumpItemLayout.removeAllComponents();
        return this;
    }

    @Override
    public Breadcrumb add(String breadcrumb) {

        if (breadcrumb != null) {
            addItem(new Label(breadcrumb));
        }

        return this;
    }

    @Override
    public Breadcrumb add(String... breadcrumbs) {
        if (breadcrumbs != null) {
            for (String breadcrumb : breadcrumbs) {
                add(breadcrumb);
            }
        }
        return this;
    }

    private void addItem(Component component) {
        component.addStyleName("monitoring-breadcrumb-item");

        if (breadcrumpItemLayout.getComponentCount() > 0) {
            Label sperator = new Label("|");
            sperator.setStyleName("monitoring-breadcrumb-seperator");
            breadcrumpItemLayout.addComponent(sperator);
        }

        breadcrumpItemLayout.addComponent(component);

    }

    @Override
    public Breadcrumb add(HorizontalLayout hLayout) {
        if (hLayout != null) {
            addItem(hLayout);
        }
        return null;
    }

}
