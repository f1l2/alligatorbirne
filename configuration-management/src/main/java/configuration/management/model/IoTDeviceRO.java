package configuration.management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class IoTDeviceRO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String authority;

    @Column
    private Date created;

    @Column
    private Date updated;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<IoTDeviceDataSourceRO> ioTDeviceDataSources = new ArrayList<IoTDeviceDataSourceRO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<IoTDeviceDataSourceRO> getIoTDeviceDataSources() {
        return ioTDeviceDataSources;
    }

    public void setIoTDeviceDataSources(List<IoTDeviceDataSourceRO> ioTDeviceDataSources) {
        this.ioTDeviceDataSources = ioTDeviceDataSources;
    }

    @Override
    public String toString() {
        return "IoTDeviceRO [id=" + id + ", name=" + name + ", url=" + authority + ", created=" + created + ", updated=" + updated + ", ioTDeviceDataSources=" + ioTDeviceDataSources + "]";
    }

}
