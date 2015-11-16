package monitoring.webapp.ui.notify.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

import monitoring.webapp.ui.notify.model.NotifyModel;

@SuppressWarnings("serial")
public class NotifyComponent extends CustomComponent implements Notify {

    public NotifyComponent() {
        super();

    }

    public NotifyComponent(Component compositionRoot) {
        super(compositionRoot);

    }

    @Override
    public void addNotify(NotifyModel<?> notifyModel) {
        NotifyManager.addNotify(this, notifyModel);

    }

}
