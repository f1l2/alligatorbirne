package common.transformer;

import common.data.DeviceInformation;
import common.data.configuration.XMLDeviceInformation;

public class XMLDeviceInformationTranformer extends Transformer<XMLDeviceInformation, DeviceInformation> {

    @Override
    public XMLDeviceInformation toLocal(DeviceInformation remote) {
        XMLDeviceInformation xMLDeviceInformation = new XMLDeviceInformation();
        xMLDeviceInformation.setName(remote.getName());
        return xMLDeviceInformation;
    }

    @Override
    public DeviceInformation toRemote(XMLDeviceInformation local) {
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName(local.getName());
        return deviceInformation;
    }

}
