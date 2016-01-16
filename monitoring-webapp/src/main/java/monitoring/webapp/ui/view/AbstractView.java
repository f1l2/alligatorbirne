package monitoring.webapp.ui.view;

import com.vaadin.navigator.View;

import monitoring.webapp.ui.breadcrumb.Breadcrumb;
import monitoring.webapp.ui.notify.component.Notify;

public interface AbstractView extends View, Notify {

    // public void setHeadLine(String value);
    //
    // public void setHeadLine(ENV environment, String value);
    //
    // public void setHeadLine(ENV environment, String format, Object... args);

    public Breadcrumb initBreadcrumb();

}
