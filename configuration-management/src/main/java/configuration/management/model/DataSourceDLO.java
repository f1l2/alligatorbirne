package configuration.management.model;

import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "DATA_SOURCE", uniqueConstraints = @UniqueConstraint(columnNames = { "domain", "device", "component" }) )
public class DataSourceDLO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String domain;

    @Column
    private String device;

    @ManyToOne()
    @JoinColumn(name = "component")
    private Component component;

    @Column
    private Properties properties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {

        StringBuilder hash = new StringBuilder();
        hash.append(device);
        hash.append("$$$");
        hash.append(domain);

        return hash.toString().hashCode();

    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof DataSourceDLO)) {
            return false;
        }

        DataSourceDLO ds = (DataSourceDLO) object;

        if (!(device.equals(ds.getDevice()))) {
            return false;
        }

        if (!(domain.equals(ds.getDomain()))) {
            return false;
        }

        return true;
    }

}
