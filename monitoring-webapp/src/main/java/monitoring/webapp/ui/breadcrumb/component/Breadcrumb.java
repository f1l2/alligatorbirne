package monitoring.webapp.ui.breadcrumb.component;

import com.vaadin.ui.HorizontalLayout;

import monitoring.webapp.service.Display;

public interface Breadcrumb {

    public Breadcrumb init();

    public Breadcrumb add(String breadcrumb);

    public Breadcrumb add(Display display);

    public Breadcrumb add(String... breadcrumbs);

    public Breadcrumb add(HorizontalLayout hLayout);
}
