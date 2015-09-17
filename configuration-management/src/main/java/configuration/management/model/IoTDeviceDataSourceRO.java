package configuration.management.model;

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
@Table(name = "DEVICE_DATA_SOURCE", uniqueConstraints = @UniqueConstraint(columnNames = { "device_id", "domain", "device" }) )
public class IoTDeviceDataSourceRO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "device_id")
    private IoTDeviceRO ioTDevice;

    @Column
    private String domain;

    @Column
    private String device;

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

    public IoTDeviceRO getIoTDevice() {
        return ioTDevice;
    }

    public void setIoTDevice(IoTDeviceRO ioTDevice) {
        this.ioTDevice = ioTDevice;
    }

    @Override
    public String toString() {
        return "IoTDeviceDataSourceRO [id=" + id + ", ioTDeviceRO=" + ioTDevice + ", domain=" + domain + ", device=" + device + "]";
    }

}
