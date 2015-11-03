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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import common.data.type.COMPONENT_TYPE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String authority;

    @Column
    private Date created;

    @Column
    private Date updated;

    @Column
    private COMPONENT_TYPE type;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<DataSourceRO> dataSources = new ArrayList<DataSourceRO>();

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

    public void setType(COMPONENT_TYPE type) {
        this.type = type;
    }

    public COMPONENT_TYPE getType() {
        return type;
    }

    public List<DataSourceRO> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSourceRO> dataSources) {
        this.dataSources = dataSources;
    }
}
