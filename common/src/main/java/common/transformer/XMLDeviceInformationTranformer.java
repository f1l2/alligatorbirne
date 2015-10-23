package common.transformer;

import common.data.model.DeviceInformation;
import common.data.setting.XMLDeviceInformation;
import common.utilities.NormalizeString;

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

        DeviceInformation deviceInformation = new DeviceInformation();
        if (null == local) {
            deviceInformation.setName(nullString);
        } else {
            deviceInformation.setName(NormalizeString.normalize(local.getName()));
        }

        return deviceInformation;
    }

}
