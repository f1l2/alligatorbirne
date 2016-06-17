package monitoring.webapp.ui.component.view;

import java.util.EventListener;

import monitoring.webapp.ui.component.component.ComponentTable;
import monitoring.webapp.ui.view.AbstractView;

public interface ComponentView extends AbstractView {
    public static final String VIEW_NAME = "/component";

    public ComponentTable getEPTable();

    public ComponentTable getDevTable();

    public interface ComponentViewListener extends EventListener {
        void refreshDevTable();

        void refreshEPTable();
    }

    public void addComponentViewListener(ComponentViewListener listener);

}
