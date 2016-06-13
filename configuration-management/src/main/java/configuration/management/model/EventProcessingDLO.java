package configuration.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import common.data.type.COMPONENT_TYPE;

@Entity
@Table(name = "EPROCESSING")
public class EventProcessingDLO extends Component {

    public EventProcessingDLO() {
        super();
        setType(COMPONENT_TYPE.DEVICE);
    }

    @Column
    private int numberOfActiveRules;

    @Column
    private int cpuUsage;

    @Column
    private int ramUsage;

    public int getNumberOfActiveRules() {
        return numberOfActiveRules;
    }

    public void setNumberOfActiveRules(int numberOfActiveRules) {
        this.numberOfActiveRules = numberOfActiveRules;
    }
}
