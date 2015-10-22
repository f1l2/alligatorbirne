package monitoring.client.commando;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.DataSource;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;
import monitoring.client.text.table.TextTable;

@Component
public class Commands implements CommandMarker {

    private RestTemplate restTemplate;

    private Connection cm;

    @Value("${cm.server.host}")
    private String host;

    @Value("${cm.server.port}")
    private int port;

    @PostConstruct
    public void postConstruct() {
        restTemplate = new RestTemplate();

        UrlUtils.parseUrl(host + ":" + Integer.toString(port));

        cm = new Connection();
        cm.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        cm.setName("CM");
        cm.setUrl(UrlUtils.parseUrl(host + ":" + Integer.toString(port)));
    }

    /**
     * Command for retrieving all running EP instances.
     * 
     * @return
     */
    @CliCommand(value = "list-ep", help = "lists all running event processing instances.")
    public String listEP() {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);

            printTable(response.getBody());
            return "list-ep successfully terminated.";

        } catch (CommandsException e) {
            return e.getMessage();
        }
    }

    /**
     * Command for retrieving all running devices.
     * 
     * @return
     */
    @CliCommand(value = "list-dev", help = "Lists all running devices")
    public String listDev() {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES, cm);

            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);

            printTable(response.getBody());
        } catch (CommandsException e) {
            return e.getMessage();
        }

        return "list-dev successfully terminated";

    }

    /**
     * Command for registration of a query.
     * 
     * @param id
     *            of EP.
     * @param queryName
     *            name of query. Used for identification.
     * @param query
     *            query, which complies with the syntax defined by the framework.
     * @return
     */
    @CliCommand(value = "rq", help = "Register query")
    public String registerQuery(@CliOption(key = { "epId" }, mandatory = true, help = "id of EP") final long id,
            @CliOption(key = { "queryName" }, mandatory = true, help = "name of query") final String queryName,
            //
            @CliOption(key = { "query" }, mandatory = true, help = "query") final String query) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY, getEPById(id));
            url = StringUtils.replace(url, "{name}", queryName);

            ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, query, String.class);

            return postForEntity.getBody();
        } catch (CommandsException e) {
            return e.getMessage();
        }
    }

    /**
     * Command for retrieving all registered queries.
     * 
     * @param id
     *            of EP.
     * @return
     */
    @CliCommand(value = "list-rq", help = "list all registered queries")
    public String listRegisteredQuery(@CliOption(key = { "epId" }, mandatory = true, help = "id of EP") final long id) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES, getEPById(id));

            ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

            printTable(response.getBody());
        } catch (CommandsException e) {
            return e.getMessage();
        }

        return "list-rq successfully terminated";
    }

    /**
     * Command for registration of a rule.
     * 
     * @param id
     *            of EP.
     * @param ruleName
     *            name of rule. Used for identification.
     * @param rule
     *            rule, which complies with the syntax defined by the framework.
     * @return
     */
    @CliCommand(value = "rr", help = "Register rule")
    public String registerRule(@CliOption(key = { "epId" }, mandatory = true, help = "id of EP") final long id,
            @CliOption(key = { "ruleName" }, mandatory = true, help = "name of rule") final String ruleName,
            //
            @CliOption(key = { "rule" }, mandatory = true, help = "rule (contains name of query)") final String rule) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE, getEPById(id));
            url = StringUtils.replace(url, "{name}", ruleName);

            ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, rule, String.class);

            return postForEntity.getBody();
        } catch (CommandsException e) {
            return e.getMessage();
        }
    }

    /**
     * Command for retrieving all registered rules.
     * 
     * @param id
     *            of EP.
     * @return
     */
    @CliCommand(value = "list-rr", help = "list registered rules")
    public String listRegisteredRule(@CliOption(key = { "epId" }, mandatory = true, help = "id of EP") final long id) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES, getEPById(id));
            ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

            printTable(response.getBody());
        } catch (CommandsException e) {
            return e.getMessage();
        }

        return "list-rr successfully terminated";
    }

    @CliCommand(value = "list-ds", help = "retrieves data sources by id")
    public String dataSourcesById(@CliOption(key = { "devId" }, mandatory = true, help = "id of device") final long id) {

        try {

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES, cm, getDeviceById(id).getId());

            ResponseEntity<DataSource[]> response = restTemplate.getForEntity(url, DataSource[].class);

            printTable(Arrays.asList(response.getBody()));

        } catch (CommandsException e) {
            return e.getMessage();
        }

        return "list-ds successfully terminated";
    }

    @CliCommand(value = "activate-rule", help = "activate rule")
    public String activateRule(@CliOption(key = { "epId" }, mandatory = true, help = "id of EP") final long id,
            @CliOption(key = { "ruleName" }, mandatory = true, help = "name of rule") final String ruleName) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_ACTIVATIONS_RULE, getEPById(id), ruleName);

            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

            return result.getBody();

        } catch (CommandsException e) {
            return e.getMessage();
        }
    }

    @CliCommand(value = "list-dev-by", help = "retrieves data sources by id")
    public String dataSourcesByIdDevInfoDomainInfo(@CliOption(key = { "devInfo" }, mandatory = true, help = "name of dev info") String devInfo,
            //
            @CliOption(key = { "domInfo" }, mandatory = true, help = "name of dom info") String domInfo) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGGT_GET_DEVICE_BY_DATA_SOURCES, cm, devInfo, domInfo);

            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);

            printTable(response.getBody());

        } catch (CommandsException e) {
            return e.getMessage();
        }

        return "list-dev-by successfully terminated.";
    }

    /**
     * Yields EP by Id.
     * 
     * @param id
     * @return
     * @throws CommandsException
     */
    private Connection getEPById(Long id) throws CommandsException {
        return getById(id, COMPONENT_TYPE.EVENT_PROCESSING);
    }

    /**
     * Yields Device by Id.
     * 
     * @param id
     * @return
     * @throws CommandsException
     */
    private Connection getDeviceById(Long id) throws CommandsException {
        return getById(id, COMPONENT_TYPE.IOT_DEVICE);
    }

    /**
     * Yields Instance by Id.
     * 
     * @param id
     * @param ct
     * @return
     * @throws CommandsException
     */
    private Connection getById(Long id, COMPONENT_TYPE ct) throws CommandsException {
        String url;
        if (COMPONENT_TYPE.EVENT_PROCESSING.equals(ct)) {
            url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);
        } else if (COMPONENT_TYPE.IOT_DEVICE.equals(ct)) {
            url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES, cm);
        } else {
            return null;
        }

        ResponseEntity<Connection[]> responseEntity = restTemplate.getForEntity(url, Connection[].class);

        List<Connection> list = Arrays.asList(responseEntity.getBody());

        Optional<Connection> findById = list.stream().filter(item -> item.getId() == id).findFirst();

        if (findById == null) {
            throw new CommandsException("Couldn't locate EP with id = " + id);
        }

        return findById.get();
    }

    /**
     * Transforms objects to a table model.
     *
     * @param objects
     *            - objects which have to be converted. Note that these objects have to have the same type;
     * @return
     * @throws IllegalAccessException
     *             - if the objects are not of the same type
     */
    private TableModel transformArrayToTableModel(Object[] objects) throws IllegalAccessException {

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        if (objects == null || objects.length == 0) {
            return defaultTableModel;
        }

        Field[] fields = objects[0].getClass().getDeclaredFields();
        int columnCount = fields.length;
        for (Field field : fields) {
            defaultTableModel.addColumn(field.getName());
        }

        Object[] rowData = null;
        Object currentObject = null;
        Field currentField = null;
        for (int row = 0; row < objects.length; row++) {
            rowData = new Object[columnCount];
            currentObject = objects[row];
            for (int col = 0; col < columnCount; col++) {
                currentField = currentObject.getClass().getDeclaredFields()[col];
                currentField.setAccessible(true);
                rowData[col] = currentField.get(currentObject);
            }
            defaultTableModel.addRow(rowData);
        }
        return defaultTableModel;
    }

    private String printTable(Object[] objects) throws CommandsException {

        if (0 == objects.length) {
            throw new CommandsException("No instance.");
        }

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(objects);
        } catch (IllegalAccessException e) {
            throw new CommandsException("No printable data received.");
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();

        return "";
    }

    private String printTable(List<DataSource> ds) throws CommandsException {

        if (0 == ds.size()) {
            throw new CommandsException("No instance.");
        }

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("devInfo");
        tableModel.addColumn("domainInfo");

        for (DataSource dataSource : ds) {
            Object[] rowData = new Object[2];
            rowData[0] = dataSource.getDeviceInformation().getName();
            rowData[1] = dataSource.getDomainInformation().getName();
            tableModel.addRow(rowData);
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();

        return host;

    }

    class CommandsException extends Exception {

        private static final long serialVersionUID = 7334900954607158440L;

        private String message;

        public CommandsException(String message) {
            this.setMessage(message);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
