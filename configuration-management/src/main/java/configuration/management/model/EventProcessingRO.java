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
@Table(name = "EPROCESSING")
public class EventProcessingRO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String authority;

    @Column
    private Date date;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<EventProcessingDataSourceRO> eventProcessingDataSources = new ArrayList<EventProcessingDataSourceRO>();

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<EventProcessingDataSourceRO> getEventProcessingDataSources() {
        return eventProcessingDataSources;
    }

    public void setEventProcessingDataSources(List<EventProcessingDataSourceRO> eventProcessingDataSources) {
        this.eventProcessingDataSources = eventProcessingDataSources;
    }

    @Override
    public String toString() {
        return "EventProcessingRO [id=" + id + ", name=" + name + ", url=" + authority + ", eventProcessingDataSources=" + eventProcessingDataSources + "]";
    }

}
