package iot.device.property;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class Configuration {

    /**
     * Set system reserved properties
     */
    public Configuration() {
        properties.putAll(SystemReservedProperty.getSystemReservedProperty());
    }

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void mergeProperties(Properties prop) {

        Properties systemReservedProperties = new Properties();
        Properties customProperties = new Properties();

        /**
         * Split properties
         */

        for (Object key : prop.keySet()) {
            if ((key instanceof String) && (properties.containsKey(key))) {
                systemReservedProperties.put(key, prop.get(key));
            } else {
                customProperties.put(key, prop.get(key));
            }
        }

        this.mergeSystemReservedProperties(systemReservedProperties);
        this.mergeCustomProperties(customProperties);
    }

    private void mergeSystemReservedProperties(Properties prop) {

        for (Object key : prop.keySet()) {

            int value = (int) prop.get(key);
            int min = SystemReservedProperty.getSystemReservedPropertyByName((String) key).getMin();
            int max = SystemReservedProperty.getSystemReservedPropertyByName((String) key).getMax();

            if ((compare(min, value, isSmaller) && (this.compare(max, value, isGreater)))) {

                this.properties.put(key, prop.get(key));
            }
        }

    }

    private void mergeCustomProperties(Properties prop) {
        this.properties.putAll(prop);
    }

    private Operation isGreater = new Operation() {
        public boolean operate(int a, int b) {
            return (a >= b);
        }
    };

    private Operation isSmaller = new Operation() {
        public boolean operate(int a, int b) {
            return (a <= b);
        }
    };

    public boolean compare(int a, int b, Operation o) {
        return o.operate(a, b);
    }

    interface Operation {
        boolean operate(int a, int b);
    }

}
