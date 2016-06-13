package monitoring.webapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.servlet.ServletException;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.DataSource;
import common.data.dto.LogDTO;
import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;

@Component
@ConfigurationProperties("c")
public class MonitoringService {

    @Autowired
    private MessagingHandlerImpl messagingHandler;

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private PrototypeProperties properties;

    private RestTemplate restTemplate;

    private Connection connectionCM;

    private LoggingDB loggingDB;

    /**
     * Initialize Connection to CM
     * 
     * @throws ServletException
     * @throws SQLException
     */
    @PostConstruct
    public void postConstruct() throws ServletException, SQLException {
        restTemplate = new RestTemplate();

        connectionCM = new Connection();
        connectionCM.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        connectionCM.setName("CM");
        connectionCM.setUrl(UrlUtils.parseUrl("localhost:5000"));

        loggingDB = new LoggingDB();
        loggingDB.startup();
    }

    @PreDestroy
    public void preDestroy() throws SQLException {
        loggingDB.shutdown();
    }

    public List<Connection> listEP() {

        Connection[] entities = getForEntity(ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_ALL_EVENT_PROCESSING, connectionCM), Connection[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<Connection>();
    }

    public List<Connection> listDev() {

        Connection[] entities = getForEntity(ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_ALL_DEVICES, connectionCM), Connection[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<Connection>();
    }

    public List<QueryDTO> listRegisteredQuery() {
        return listRegisteredQuery(connectionCM, RESOURCE_NAMING.CM_GET_ALL_QUERIES);
    }

    public List<QueryDTO> listRegisteredQuery(Connection connection) {
        return listRegisteredQuery(connection, RESOURCE_NAMING.EP_GET_ALL_QUERIES);
    }

    private List<QueryDTO> listRegisteredQuery(Connection connection, RESOURCE_NAMING resource) {

        QueryDTO[] entities = getForEntity(ResourceUtils.getUrl(resource, connection), QueryDTO[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<QueryDTO>();
    }

    public List<RuleDTO> listRegisteredRule() {
        return listRegisteredRule(connectionCM, RESOURCE_NAMING.CM_GET_ALL_RULES);
    }

    public List<RuleDTO> listRegisteredRule(Connection connection) {
        return listRegisteredRule(connection, RESOURCE_NAMING.EP_GET_ALL_RULES);
    }

    private List<RuleDTO> listRegisteredRule(Connection connection, RESOURCE_NAMING resource) {

        RuleDTO[] entities = getForEntity(ResourceUtils.getUrl(resource, connection), RuleDTO[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<RuleDTO>();
    }

    public List<DataSource> listDataSourcesById(Connection connection) {

        DataSource[] entities = getForEntity(ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_DEVICE_DATA_SOURCES, connectionCM, connection.getId()), DataSource[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<DataSource>();

    }

    public String registerQuery(final String queryName, final String query) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTRATION_QUERY, connectionCM);
        url = StringUtils.replace(url, "{name}", queryName);

        return postForEntity(url, query, String.class);

    }

    public String registerRule(final String ruleName, final String rule) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTRATION_RULE, connectionCM);
        url = StringUtils.replace(url, "{name}", ruleName);

        return postForEntity(url, rule, String.class);
    }

    public void deregisterQuery(QueryDTO query) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEREGISTRATION_QUERY, connectionCM);
        url = StringUtils.replace(url, "{name}", query.getName());

        restTemplate.delete(url);

    }

    public void deregisterRule(RuleDTO rule) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEREGISTRATION_RULE, connectionCM);
        url = StringUtils.replace(url, "{name}", rule.getName());

        restTemplate.delete(url);
    }

    public String activateRule(RuleDTO rule, int strategyNumber) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_ACTIVATIONS_RULE_STRATEGY, connectionCM, rule.getName(), String.valueOf(strategyNumber));
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        return result.getBody();
    }

    public String deactivateRule(RuleDTO rule) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEACTIVATIONS_RULE, connectionCM, rule.getName());
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        return result.getBody();
    }

    public List<LogDTO> listLogs() {

        LogDTO[] entities = getForEntity(ResourceUtils.getUrl(RESOURCE_NAMING.CM_LOGS, connectionCM), LogDTO[].class);
        return entities != null ? Arrays.asList(entities) : new ArrayList<LogDTO>();

    }

    public TopicViewMBean getProxyToTopic(String name) throws MalformedObjectNameException, JMSException {

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl("localhost:61616"));

        messagingHandler.start(connection);
        messagingHandler.consume(null, new MessageListener() {

            @Override
            public void onMessage(Message message) {
                // TODO Auto-generated method stub
            }
        });

        brokerService.setPersistent(false);
        brokerService.setAdvisorySupport(false);
        brokerService.setSchedulerSupport(true);
        brokerService.setPopulateJMSXUserID(true);
        brokerService.setSchedulerSupport(true);
        brokerService.getManagementContext().setCreateConnector(false);

        ObjectName topicViewMBeanName = new ObjectName("org.apache.activemq:type=Broker,brokerName=" + brokerService.getBrokerName() + ",destinationType=Topic,destinationName=" + name);
        TopicViewMBean proxy = (TopicViewMBean) brokerService.getManagementContext().newProxyInstance(topicViewMBeanName, TopicViewMBean.class, true);

        return proxy;
    }

    private <E> E getForEntity(String url, Class<E> responseType) {
        try {
            ResponseEntity<E> response = restTemplate.getForEntity(url, responseType);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private <E> E postForEntity(String url, String query, Class<E> responseType) {

        ResponseEntity<E> postForEntity = restTemplate.postForEntity(url, query, responseType);

        return postForEntity.getBody();
    }

    public List<LogDTO> getAllLog() {

        if (!loggingDB.isConnection()) {
            return null;
        }

        return getLogs("SELECT * FROM LOGGING_EVENT WHERE LEVEL_STRING = 'INFO' ORDER BY TIMESTMP DESC;");
    }

    public List<LogDTO> getFilteredLog(int filterCode) {

        if (!loggingDB.isConnection()) {
            return null;
        }

        String filterDEV = "LOGGER_NAME LIKE 'iot.device%'";
        String filterCM = "LOGGER_NAME LIKE 'configuration.management%'";
        String filterEP = "LOGGER_NAME LIKE 'event.processing%'";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM LOGGING_EVENT WHERE LEVEL_STRING = 'INFO'");
        if (filterCode > 0) {
            sb.append("AND NOT (");
            String filter[] = { filterEP, filterCM, filterDEV };
            for (int i = 0; i < 3; i++) {
                if ((filterCode & (1l << i)) != 0) {
                    sb.append(filter[i]);
                    filterCode = filterCode & ~(1 << i);

                    if (filterCode != 0) {
                        sb.append(" OR ");
                    }
                }
            }
            sb.append(") ");
        }
        sb.append("ORDER BY TIMESTMP DESC");

        return getLogs(sb.toString());
    }

    private List<LogDTO> getLogs(String query) {

        List<LogDTO> logs = new ArrayList<LogDTO>();
        ResultSet loggingEvents = null;
        try {

            loggingEvents = this.query(query);
            logs = convertToLogDTO(loggingEvents);
        } catch (Exception e) {
            System.out.println("Error accessing loggin db." + e);
        }
        return logs;
    }

    public void deleteAllLog() {

        try {
            this.query("DELETE FROM LOGGING_EVENT_PROPERTY WHERE 1=1;");
            this.query("DELETE FROM LOGGING_EVENT_EXCEPTION WHERE 1=1");
            this.query("DELETE FROM LOGGING_EVENT WHERE 1=1;");
        } catch (SQLException e) {
            System.out.println("Error accessing loggin db." + e);
        }
    }

    private synchronized ResultSet query(String expression) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        statement = loggingDB.getConnection().createStatement();
        resultSet = statement.executeQuery(expression);

        statement.close();

        return resultSet;
    }

    public static List<LogDTO> convertToLogDTO(ResultSet resultSet) throws SQLException {

        List<LogDTO> logs = new ArrayList<LogDTO>();

        while (resultSet.next()) {

            LogDTO logDTO = new LogDTO();
            logDTO.setDate(new Date(resultSet.getBigDecimal("TIMESTMP").toBigInteger().longValue()));
            logDTO.setMessage(resultSet.getString("FORMATTED_MESSAGE"));
            logDTO.setName(resultSet.getString("LOGGER_NAME"));

            logs.add(logDTO);
        }

        return logs;
    }

    public List<QueryDTO> getPreparedQueries() {

        return properties.getPreparedQueries();
    }

    public List<RuleDTO> getPreparedRules() {
        return properties.getPreparedRules();
    }

}
