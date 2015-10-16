package common.data.model;

import common.data.type.DOMAIN_INFORMATION_TYPE;

public class DomainInformation extends DataModel {

    private DOMAIN_INFORMATION_TYPE type;

    public DOMAIN_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DOMAIN_INFORMATION_TYPE type) {
        this.type = type;
    }
}
