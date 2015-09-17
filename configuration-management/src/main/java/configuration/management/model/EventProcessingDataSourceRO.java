package configuration.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EPROCE_DATA_SOURCE", uniqueConstraints = @UniqueConstraint(columnNames = { "eventProcessingId", "domain", "deviceInformation" }) )
public class EventProcessingDataSourceRO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long eventProcessingId;

    @Column
    private String domain;

    @Column
    private String deviceInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public Long getEventProcessingId() {
        return eventProcessingId;
    }

    public void setEventProcessingId(Long eventProcessingId) {
        this.eventProcessingId = eventProcessingId;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(String deviceInformation) {
        this.deviceInformation = deviceInformation;
    }
}
