package configuration.management.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RULE")
public class RuleDLO {

    public RuleDLO() {
    }

    public RuleDLO(String name, String rule) {
        this.name = name;
        this.rule = rule;
    }

    @Id()
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String rule;

    @OneToOne
    private EventProcessingDLO epDLO;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
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

    public EventProcessingDLO getEpDLO() {
        return epDLO;
    }

    public void setEpDLO(EventProcessingDLO epDLO) {
        this.epDLO = epDLO;
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

    public Set<QueryDLO> getQueries() {
        return queries;
    }

    public void setQueries(Set<QueryDLO> queries) {
        this.queries = queries;
    }
}
