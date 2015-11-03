package configuration.management.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import common.data.type.COMPONENT_TYPE;

@Entity
@Table(name = "DEVICE")
public class Device extends Component {

    public Device() {
        super();
        setType(COMPONENT_TYPE.DEVICE);
    }

}
