package common.data;

import common.data.type.DOMAIN_INFORMATION_TYPE;

public class DomainInformation extends DataModel {

    private String name;

    private DOMAIN_INFORMATION_TYPE type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DOMAIN_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DOMAIN_INFORMATION_TYPE type) {
        this.type = type;
    }
}
