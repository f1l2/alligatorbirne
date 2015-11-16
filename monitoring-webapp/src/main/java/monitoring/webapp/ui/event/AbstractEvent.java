package monitoring.webapp.ui.event;

@SuppressWarnings("serial")
public class AbstractEvent<S, E> extends NotifyEvent<S> {

    private final E event;

    public AbstractEvent(S source, E event) {
        super(source);
        this.event = event;
    }

    public E getEvent() {
        return event;
    }

}
