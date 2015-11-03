package configuration.management.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import common.data.type.COMPONENT_TYPE;

@Entity
@Table(name = "EPROCESSING")
public class EventProcessing extends Component {

    public EventProcessing() {
        super();
        setType(COMPONENT_TYPE.DEVICE);
    }

}
