package monitoring.webapp.ui.breadcrumb;

import com.vaadin.ui.HorizontalLayout;

public interface Breadcrumb {

    public Breadcrumb init();

    public Breadcrumb add(String breadcrumb);

    public Breadcrumb add(String... breadcrumbs);

    public Breadcrumb add(HorizontalLayout hLayout);
}
