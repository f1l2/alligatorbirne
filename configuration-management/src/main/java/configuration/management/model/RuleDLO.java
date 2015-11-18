package configuration.management.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RULE")
public class RuleDLO {
    @Id()
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String rule;

    @Column
    private boolean isActive;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "QUERY_TO_RULE",
    //
    joinColumns = { @JoinColumn(name = "RULE_ID", referencedColumnName = "ID") },
    //
    inverseJoinColumns = { @JoinColumn(name = "QUERY_ID", referencedColumnName = "ID") })
    private Set<QueryDLO> queries;

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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<QueryDLO> getQueries() {
        return queries;
    }

    public void setQueries(Set<QueryDLO> queries) {
        this.queries = queries;
    }
}
