package common.data.model;

import common.data.type.DOMAIN_INFORMATION_TYPE;

public class DomainInformation extends DataModel {

    private static final long serialVersionUID = -8125098903239926993L;

    private DOMAIN_INFORMATION_TYPE type;

    public DOMAIN_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DOMAIN_INFORMATION_TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DomainInformation [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
}
