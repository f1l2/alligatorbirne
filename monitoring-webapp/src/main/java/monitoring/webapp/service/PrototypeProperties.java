package monitoring.webapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PrototypeProperties {

    private Properties properties = new Properties();

    private InputStream inputStream = null;

    private PrototypeProperties() {

        try {

            String filename = "monitoring.properties";
            inputStream = MonitoringService.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                System.out.println("Unable to find properties file " + filename);
            }

            properties.load(inputStream);

        } catch (IOException ex) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> getPreparedQueries() {

        List<String> preparedQueries = new ArrayList<String>();

        for (int i = 1;; i++) {
            Object object = properties.get("query" + i);
            if (object != null) {
                preparedQueries.add(object.toString());
            } else {
                break;
            }
        }

        return preparedQueries;
    }

    public List<String> getPreparedRules() {
        List<String> preparedRules = new ArrayList<String>();

        for (int i = 1;; i++) {
            Object object = properties.get("rule" + i);
            if (object != null) {
                preparedRules.add(object.toString());
            } else {
                break;
            }
        }
        return preparedRules;
    }

}
