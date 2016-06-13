package monitoring.webapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;

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

    public List<QueryDTO> getPreparedQueries() {
        List<QueryDTO> preparedQueries = new ArrayList<QueryDTO>();

        for (int i = 1;; i++) {
            Object query = properties.get("query.query" + i);
            Object name = properties.get("query.name" + i);
            if ((query != null) && (name != null)) {

                QueryDTO queryDTO = new QueryDTO();
                queryDTO.setName(name.toString());
                queryDTO.setQuery(query.toString());

                preparedQueries.add(queryDTO);
            } else {
                break;
            }
        }
        return preparedQueries;
    }

    public List<RuleDTO> getPreparedRules() {
        List<RuleDTO> preparedRules = new ArrayList<RuleDTO>();

        for (int i = 1;; i++) {
            Object rule = properties.get("rule.rule" + i);
            Object name = properties.get("rule.name" + i);
            if ((rule != null) && (name != null)) {

                RuleDTO ruleDTO = new RuleDTO();
                ruleDTO.setName(name.toString());
                ruleDTO.setRule(rule.toString());

                preparedRules.add(ruleDTO);
            } else {
                break;
            }
        }
        return preparedRules;
    }
}
