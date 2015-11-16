package monitoring.webapp.ui.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public abstract class NotifyEvent<S> extends EventObject {

    public NotifyEvent(S source) {
        super(source);

    }

    @SuppressWarnings("unchecked")
    @Override
    public S getSource() {
        return (S) super.getSource();
    }

}
