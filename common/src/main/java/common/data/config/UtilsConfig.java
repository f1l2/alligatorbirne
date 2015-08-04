package common.data.config;

import java.io.File;
import java.net.URI;
import java.util.List;

import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.data.configuration.Configuration;
import common.data.configuration.ConnectionConfig;
import common.data.configuration.Connections;
import common.data.xml.XMLParser;
import common.transformer.ConfigTransformer;

public class UtilsConfig {

    public static ConnectionConfig getCMConnection() {

        for (ConnectionConfig connectionConfig : loadConnections().getConnectionConfig()) {
            if ("CM".equals(connectionConfig.getComponent())) {
                return connectionConfig;
            }
        }
        return null;
    }

    public static ConnectionConfig getEPConnection() {

        for (ConnectionConfig connectionConfig : loadConnections().getConnectionConfig()) {
            if ("EP".equals(connectionConfig.getComponent())) {
                return connectionConfig;
            }
        }
        return null;
    }

    public static Connections loadConnections() {

        Configuration configuration = loadConfiguration();

        return configuration.getConnections();
    }

    public static MeasurementData loadMeasurementData() {

        Configuration configuration = loadConfiguration();
        ConfigTransformer transformer = new ConfigTransformer();

        List<MeasurementPoint> remote = transformer.toRemote(configuration.getMeasurementDataConfig().getMeasurementPointConfig());

        MeasurementData measurementData = new MeasurementData();
        measurementData.add(remote);

        return measurementData;
    }

    private static Configuration loadConfiguration() {

        String configurationStr = "src/main/resources/configuration.xml";
        File configurationFile = new File(configurationStr);

        URI configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        return XMLParser.unmarshal(configurationURI);
    }

}
