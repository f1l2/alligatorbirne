package monitoring.webapp.ui.browser.model;

public class BrowserResource {

    private final String name;
    private final String uri;

    public BrowserResource(String name, String uri) {
        super();
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

}
