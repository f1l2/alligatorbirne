package monitoring.webapp.ui.event;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.function.Consumer;

import monitoring.webapp.ui.LazyLoading;

public class EventListenerManager<EL extends EventListener> {

    private final List<EL> eventListeners = new ArrayList<EL>();

    public boolean addEventListener(EL eventListener) {

        if (eventListener != null) {
            if (eventListeners.contains(eventListener)) {
                eventListeners.remove(eventListener);
            }
            return eventListeners.add(eventListener);
        }

        return false;

    }

    public boolean removeEventListener(EL eventListener) {

        if (eventListener != null) {
            return eventListeners.remove(eventListener);
        }

        return false;

    }

    public void fireEvent(Consumer<? super EL> action) {

        LazyLoading.load(new Runnable() {
            @Override
            public void run() {
                for (EL eventListener : eventListeners) {
                    action.accept(eventListener);
                }
            }
        });

    }
}
