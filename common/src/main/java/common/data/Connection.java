package common.data;

import java.net.URL;

public class Connection {

    private long id;

    private String name;

    private URL url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Connection [id=" + id + ", name=" + name + ", url=" + url + "]";
    }

}
