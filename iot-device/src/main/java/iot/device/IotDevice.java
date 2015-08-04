package iot.device;

import iot.device.status.Status;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.data.configuration.Configuration;
import common.data.configuration.ConnectionConfig;
import common.data.configuration.Connections;
import common.transformer.TransformConfig;
import common.transformer.TransformXML;

@Controller
public class IotDevice {

    final static Logger logger = Logger.getLogger(IotDevice.class);

    @Autowired
    private Status status;

    public void setLocalConfiguration(String ip, String port) {
        // TODO
    }

    public ConnectionConfig getCMConnection() {

        for (ConnectionConfig connectionConfig : loadConnections().getConnectionConfig()) {
            if ("CM".equals(connectionConfig.getComponent())) {
                return connectionConfig;
            }
        }
        return null;
    }

    private Connections loadConnections() {

        Configuration configuration = loadConfiguration();

        return configuration.getConnections();
    }

    public MeasurementData loadMeasurementData() {

        Configuration configuration = loadConfiguration();
        TransformConfig transformer = new TransformConfig();

        List<MeasurementPoint> remote = transformer.toRemote(configuration.getMeasurementDataConfig().getMeasurementPointConfig());

        MeasurementData measurementData = new MeasurementData();
        measurementData.add(remote);

        return measurementData;
    }

    private Configuration loadConfiguration() {

        String configurationStr = "src/main/resources/configuration.xml";
        File configurationFile = new File(configurationStr);

        URI configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        return TransformXML.unmarshal(configurationURI);
    }
}
