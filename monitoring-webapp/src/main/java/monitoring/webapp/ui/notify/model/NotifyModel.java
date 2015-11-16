package monitoring.webapp.ui.notify.model;

public abstract class NotifyModel<N extends NotifyModel<N>> {

    private String caption;
    private String description;
    private String style;

    public String getCaption() {
        return caption;
    }

    public N setCaption(String format, Object... args) {
        return setCaption(String.format(format, args));
    }

    @SuppressWarnings("unchecked")
    public N setCaption(String caption) {
        this.caption = caption;
        return (N) this;
    }

    public String getDescription() {
        return description;
    }

    public N setDescription(String format, Object... args) {
        return setDescription(String.format(format, args));
    }

    @SuppressWarnings("unchecked")
    public N setDescription(String description) {
        this.description = description;
        return (N) this;
    }

    public String getStyle() {
        return style;
    }

    @SuppressWarnings("unchecked")
    public N setStyle(String style) {
        this.style = style;
        return (N) this;
    }

}
