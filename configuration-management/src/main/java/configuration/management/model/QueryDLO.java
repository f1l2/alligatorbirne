package configuration.management.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUERY")
public class QueryDLO {

    public QueryDLO() {
    }

    public QueryDLO(String name, String query) {
        this.name = name;
        this.query = query;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String query;

    @ManyToMany(mappedBy = "queries")
    private List<RuleDLO> rules;

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<RuleDLO> getRules() {
        return rules;
    }

    public void setRules(List<RuleDLO> rules) {
        this.rules = rules;
    }
}
