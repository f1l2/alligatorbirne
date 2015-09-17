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
@Table(name = "EPROCESSING_DATA_SOURCE", uniqueConstraints = @UniqueConstraint(columnNames = { "event_processing_id", "domain", "device" }) )
public class EventProcessingDataSourceRO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String domain;

    @Column
    private String device;

    @ManyToOne()
    @JoinColumn(name = "event_processing_id", unique = true)
    private EventProcessingRO eventProcessing;

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

    public EventProcessingRO getEventProcessing() {
        return eventProcessing;
    }

    public void setEventProcessing(EventProcessingRO eventProcessing) {
        this.eventProcessing = eventProcessing;
    }
}
