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
    @CliCommand(value = "list-ep", help = "Lists all running event processing instances.")
    public String listEP() {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

        ResponseEntity<Connection[]> responseEntity = restTemplate.getForEntity(url, Connection[].class);

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(responseEntity.getBody());
        } catch (IllegalAccessException e) {
            return "CM has not delivered valid data.";
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();
        return "list-ep call successfully performed.";
    }

    /**
     * Command for retrieving all running devices.
     * 
     * @return
     */
    @CliCommand(value = "list-dev", help = "Lists all running devices")
    public String listDev() {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES, cm);

        ResponseEntity<Connection[]> responseEntity = restTemplate.getForEntity(url, Connection[].class);

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(responseEntity.getBody());
        } catch (IllegalAccessException e) {
            return "CM has not delivered valid data.";
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();
        return "list-dev call successfully performed.";
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

        Optional<Connection> findById = getEPById(id);
        if (findById == null) {
            return "Couldn't locate EP with id = " + id;
        }

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY, findById.get());
        url = StringUtils.replace(url, "{name}", queryName);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, query, String.class);

        return postForEntity.getBody();

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

        Optional<Connection> findById = getEPById(id);
        if (findById == null) {
            return "Couldn't locate EP with id = " + id;
        }

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES, findById.get());

        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(response.getBody());
        } catch (IllegalAccessException e) {
            return "EP has not delivered valid data.";
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();
        return "list-rq call successfully performed.";
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

        Optional<Connection> findById = getEPById(id);
        if (findById == null) {
            return "Couldn't locate EP with id = " + id;
        }

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE, findById.get());
        url = StringUtils.replace(url, "{name}", ruleName);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, rule, String.class);

        return postForEntity.getBody();

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

        Optional<Connection> findById = getEPById(id);
        if (findById == null) {
            return "Couldn't locate EP with id = " + id;
        }

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES, findById.get());

        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(response.getBody());
        } catch (IllegalAccessException e) {
            return "EP has not delivered valid data.";
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();
        return "list-rr call successfully performed.";
    }

    @CliCommand(value = "list-ds", help = "retrieves data sources by id")
    public String dataSourcesById(@CliOption(key = { "devId" }, mandatory = true, help = "id of device") final long id) {

        Optional<Connection> findById = getDeviceById(id);
        if (findById == null) {
            return "Couldn't locate device with id = " + id;
        }

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES, cm);
        url = url.replace("{id}", Long.toString(findById.get().getId()));

        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        TableModel tableModel;
        try {
            tableModel = transformArrayToTableModel(response.getBody());
        } catch (IllegalAccessException e) {
            return "Device has not delivered valid data.";
        }

        TextTable textTable = new TextTable(tableModel);
        textTable.setAddRowNumbering(true);
        textTable.printTable();
        return "ds call successfully performed.";

    }

    /**
     * Yields EP by Id.
     * 
     * @param id
     * @return
     */
    private Optional<Connection> getEPById(Long id) {
        return getById(id, COMPONENT_TYPE.EVENT_PROCESSING);
    }

    /**
     * Yields Device by Id.
     * 
     * @param id
     * @return
     */
    private Optional<Connection> getDeviceById(Long id) {
        return getById(id, COMPONENT_TYPE.IOT_DEVICE);
    }

    /**
     * Yields Instance by Id.
     * 
     * @param id
     * @param ct
     * @return
     */
    private Optional<Connection> getById(Long id, COMPONENT_TYPE ct) {
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

        return findById;
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
}
