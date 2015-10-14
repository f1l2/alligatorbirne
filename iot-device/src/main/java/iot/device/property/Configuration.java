package iot.device.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Configuration {

    /**
     * Set system reserved properties
     */
    public Configuration() {
        systemReservedProperties.putAll(SystemReservedProperty.getSystemReservedProperty());
    }

    /**
     * (String, Integer)
     */
    private Map<String, Integer> systemReservedProperties = new HashMap<String, Integer>();

    /**
     * (String, String)
     */
    private Map<String, String> supplyingSensor = new HashMap<String, String>();

    /**
     * (Object, Object)
     */
    private Map<Object, Object> otherProperties = new HashMap<Object, Object>();

    /**
     * Get srp value. By design of datatype int.
     * 
     */

    public int getValue(SystemReservedProperty srp) {
        return systemReservedProperties.get(srp.getName());
    }

    public Set<String> getSupplyingSensor() {
        return supplyingSensor.values().stream().collect(Collectors.toSet());
    }

    public void setAndUpdateProperties(Properties prop) {

        /**
         * Split properties
         */
        for (Object key : prop.keySet()) {

            Object value = prop.get(key);

            if ((key instanceof String) && (SystemReservedProperty.getSystemReservedProperty().containsKey((String) key))) {

                try {
                    mergeSystemReservedProperties((String) key, (int) value);
                } catch (Exception e) {

                }

            } else if ((key instanceof String) && (SensorReservedProperty.SUPPLY_REQ.name().equals((String) key))) {

                try {
                    mergeSupplyingSensorProperties((String) key, (String) value);
                } catch (Exception e) {

                }

            } else {
                otherProperties.put(key, prop.get(key));
            }
        }

    }

    public Properties getProperties() {

        Properties prop = new Properties();
        prop.putAll(systemReservedProperties);
        prop.putAll(supplyingSensor);
        prop.putAll(otherProperties);

        return prop;

    }

    private void mergeSystemReservedProperties(String key, int value) {

        int min = SystemReservedProperty.getSystemReservedPropertyByName(key).getMin();
        int max = SystemReservedProperty.getSystemReservedPropertyByName(key).getMax();

        if ((compare(min, value, isSmaller) && (this.compare(max, value, isGreater)))) {
            systemReservedProperties.put(key, value);
        }

    }

    private void mergeSupplyingSensorProperties(String key, String value) {
        supplyingSensor.put(key, value);
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
