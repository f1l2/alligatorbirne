package iot.device.property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import common.property.SensorReservedProperty;
import common.property.SystemReservedProperty;
import common.utilities.NormalizeString;
import iot.device.delivery.DeliveryTask;
import iot.device.utility.ArrayMapDeserializerStringInteger;
import iot.device.utility.ArrayMapDeserializerStringString;

@Component
public class Configuration {

    final static Logger logger = LoggerFactory.getLogger(DeliveryTask.class);

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
        return systemReservedProperties.get(srp.getName());
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

            if (!(key instanceof String)) {
                otherProperties.put(key, prop.get(key));
                continue;
            }

            String keyStr = NormalizeString.normalize((String) key);

            if (SystemReservedProperty.getSystemReservedProperty().containsKey(keyStr)) {
                try {
                    mergeSystemReservedProperties(keyStr, (int) value);
                } catch (Exception e) {
                    logger.error("{}", e);
                }
            } else if (SensorReservedProperty.SUPPLY_REQ.getName().equals(keyStr)) {

                try {
                    mergeSupplyingSensorProperties(keyStr, (String) value);
                } catch (Exception e) {
                    logger.error("{}", e);
                }
            } else {
                otherProperties.put(keyStr, prop.get(key));
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
