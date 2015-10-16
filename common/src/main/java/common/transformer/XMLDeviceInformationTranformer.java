package common.transformer;

import common.data.model.DeviceInformation;
import common.data.setting.XMLDeviceInformation;

public class XMLDeviceInformationTranformer extends Transformer<XMLDeviceInformation, DeviceInformation> {

    @Override
    public XMLDeviceInformation toLocal(DeviceInformation remote) {

        if (null == remote) {
            return null;
        }

        XMLDeviceInformation xMLDeviceInformation = new XMLDeviceInformation();
        xMLDeviceInformation.setName(remote.getName());

        return xMLDeviceInformation;
    }

    @Override
    public DeviceInformation toRemote(XMLDeviceInformation local) {

        if (null == local) {
            return null;
        }

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName(local.getName());

        return deviceInformation;
    }

}
