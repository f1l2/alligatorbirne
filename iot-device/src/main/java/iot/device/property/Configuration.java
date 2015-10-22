package iot.device.property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import common.property.SensorReservedProperty;
import common.property.SystemReservedProperty;
import iot.device.utility.ArrayMapDeserializerStringInteger;
import iot.device.utility.ArrayMapDeserializerStringString;

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
    @JsonDeserialize(using = ArrayMapDeserializerStringInteger.class)
    private Map<String, Integer> systemReservedProperties = new HashMap<String, Integer>();

    /**
     * (String, String)
     */
    @JsonDeserialize(using = ArrayMapDeserializerStringString.class)
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
        return systemReservedProperties.get(srp.name());
    }

    public List<String> getSupplyingSensor() {

        return supplyingSensor.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
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

            } else if ((key instanceof String) && (SensorReservedProperty.SUPPLY_REQ.getName().equals((String) key))) {

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Entry<Object, Object> e : getProperties().entrySet()) {
            sb.append(e.getKey());
            sb.append(" - ");
            sb.append(e.getValue());
            sb.append("; ");
        }

        return sb.toString();
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
