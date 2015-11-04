package common.data.model;

public abstract class DataModel {

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
