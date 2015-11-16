package monitoring.webapp.ui.notify.component;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import monitoring.webapp.ui.notify.model.NotifyModel;

public final class NotifyManager {

    public static void addNotify(Component component, NotifyModel<?> notifyModel) {

        Notification notify = new Notification(notifyModel.getCaption(), notifyModel.getDescription());
        notify.setStyleName(notifyModel.getStyle());
        notify.setDelayMsec(3000);
        notify.setPosition(Position.TOP_LEFT);
        notify.show(Page.getCurrent());

    }

}
