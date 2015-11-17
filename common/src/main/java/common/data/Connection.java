package common.data;

import java.net.URL;
import java.util.Date;

import common.data.type.COMPONENT_TYPE;

public class Connection {

    private long id;

    private String name;

    private URL url;

    private COMPONENT_TYPE componentType;

    private Date updated;

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

    public COMPONENT_TYPE getComponentType() {
        return componentType;
    }

    public void setComponentType(COMPONENT_TYPE componentType) {
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "Connection [id=" + id + ", name=" + name + ", url=" + url + ", type=" + componentType + "]";
    }

    public boolean equals(Connection connection) {

        if (null == connection) {
            return false;
        }

        if (null == connection.getUrl()) {
            return false;
        }

        if (getUrl().getAuthority().equals(connection.getUrl().getAuthority())) {
            return true;
        }

        return false;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
