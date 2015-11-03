package configuration.management.util;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import configuration.management.model.Component;
import configuration.management.model.DataSourceRO;

public class DataSourceUtil {

    public static void saveAndUpdate(Component component, DeviceInformation device, DomainInformation domain) {

        boolean isNew = true;

        for (DataSourceRO dataSource : component.getDataSources()) {
            if ((dataSource.getDevice().equals(device.getName())) && (dataSource.getDomain().equals(domain.getName()))) {
                isNew = false;
                break;
            }
        }

        if (isNew) {
            DataSourceRO dataSource = new DataSourceRO();
            dataSource.setComponent(component);
            dataSource.setDevice(device.getName());
            dataSource.setDomain(domain.getName());

            component.getDataSources().add(dataSource);
        }
    }

    public static void delete(Component component, DeviceInformation device, DomainInformation domain) {

        DataSourceRO delete = null;
        for (DataSourceRO dataSource : component.getDataSources()) {
            if ((dataSource.getDevice().equals(device.getName())) && (dataSource.getDomain().equals(domain.getName()))) {

                delete = dataSource;
                break;
            }
        }

        if (null != delete) {
            component.getDataSources().remove(delete);
        }
    }
}
