package configuration.management.model;

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

}
