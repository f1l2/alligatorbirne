package common.data.model;

import java.io.Serializable;

public abstract class DataModel implements Serializable {

    private static final long serialVersionUID = 5706720535491286563L;

    protected long id;

    protected String name;

    public DataModel() {
        this.id = -1l;
        this.name = "default";
    }

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
}
