package common.data;

import java.io.Serializable;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

public class DataSource implements Serializable {

    private static final long serialVersionUID = -3777645221502788551L;

    private DomainInformation domainInformation;

    private DeviceInformation deviceInformation;

    public DataSource() {
        this.domainInformation = new DomainInformation();
        this.deviceInformation = new DeviceInformation();
    }

    public DataSource(DomainInformation domain, DeviceInformation deviceInformation) {
        this.setDomainInformation(domain);
        this.setDeviceInformation(deviceInformation);
    }

    public DomainInformation getDomainInformation() {
        return domainInformation;
    }

    public void setDomainInformation(DomainInformation domainInformation) {
        this.domainInformation = domainInformation;
    }

    public DeviceInformation getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    @Override
    public String toString() {
        return "DataSource [domainInformation=" + domainInformation + ", deviceInformation=" + deviceInformation + "]";
    }

    @Override
    public int hashCode() {

        StringBuilder hash = new StringBuilder();
        hash.append(deviceInformation.getName());
        hash.append("$$$");
        hash.append(domainInformation.getName());

        return hash.toString().hashCode();

    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof DataSource)) {
            return false;
        }

        DataSource ds = (DataSource) object;

        if (!(deviceInformation.getName().equals(ds.getDeviceInformation().getName()))) {
            return false;
        }

        if (!(domainInformation.getName().equals(ds.getDomainInformation().getName()))) {
            return false;
        }

        return true;
    }
}
