package configuration.management.util;

import java.util.HashSet;
import java.util.Properties;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.property.SensorReservedProperty;
import configuration.management.model.Component;
import configuration.management.model.DataSourceRO;

public class DataSourceUtil {

    public static void saveAndUpdate(Component component, DeviceInformation device, DomainInformation domain, Properties properties) {

        /**
         * Split properties
         */

        HashSet<String> sensorData = new HashSet<String>();
        Properties props = new Properties();

        splitProperties(properties, sensorData, props);

        boolean isNew = true;

        /**
         * Check if data source is new.
         * 
         * If not merge properties.
         */
        for (DataSourceRO dataSource : component.getDataSources()) {

            if ((dataSource.getDevice().equals(device.getName())) && (dataSource.getDomain().equals(domain.getName()))) {
                isNew = false;
                mergeDataSource(dataSource, sensorData, props);
                break;
            }
        }

        /**
         * Data Source doesn't exist.
         */

        if (isNew) {

            DataSourceRO dataSource = new DataSourceRO();
            dataSource.setComponent(component);
            dataSource.setDevice(device.getName());
            dataSource.setDomain(domain.getName());
            dataSource.setProperties(properties);
            dataSource.setSensorData(sensorData);
            sensorData.forEach(item -> System.out.println(item.toString()));

            component.getDataSources().add(dataSource);
        }
    }

    private static void mergeDataSource(DataSourceRO dataSource, HashSet<String> sensorData, Properties props) {
        if (null == dataSource.getProperties()) {
            dataSource.setProperties(new Properties());
        }
        dataSource.getProperties().putAll(props);

        if (null == dataSource.getSensorData()) {
            dataSource.setSensorData(new HashSet<String>());
        }

        sensorData.forEach(item -> System.out.println(item.toString()));

        dataSource.setSensorData(sensorData);
    }

    private static void splitProperties(Properties properties, HashSet<String> sensorData, Properties props) {
        if (null != properties) {
            for (Object key : properties.keySet()) {

                Object value = properties.get(key);

                if ((value instanceof String) && (key.equals(SensorReservedProperty.REQUEST_FOR_DELIVERY.getName()))) {
                    sensorData.add((String) value);
                } else {
                    props.put(key, value);
                }
            }
        }
    }

    public static void delete(Component component, DeviceInformation device, DomainInformation domain, Properties properties) {

        String value = properties.getProperty(SensorReservedProperty.STOP_DELIVERY.getName());

        DataSourceRO delete = null;
        for (DataSourceRO dataSource : component.getDataSources()) {
            if ((dataSource.getDevice().equals(device.getName())) && (dataSource.getDomain().equals(domain.getName()))) {

                if (null != dataSource.getSensorData()) {
                    dataSource.getSensorData().remove(value);
                } else {
                    delete = dataSource;
                    break;
                }

                if (dataSource.getSensorData().size() == 0) {
                    delete = dataSource;
                }

                break;
            }
        }

        if (null != delete)

        {
            component.getDataSources().remove(delete);
        }
    }
}
