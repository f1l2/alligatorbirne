package monitoring.client.commando;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.rest.UtilsUrl;
import monitoring.client.text.table.TextTable;

@Component
public class MonitoringCommands implements CommandMarker {

    private RestTemplate restTemplate;

    private Connection cm;

    @Value("${cm.server.host}")
    private String host;

    @Value("${cm.server.port}")
    private int port;

    @PostConstruct
    public void postConstruct() {
        restTemplate = new RestTemplate();

        UtilsUrl.parseUrl(host + ":" + Integer.toString(port));

        cm = new Connection();
        cm.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        cm.setName("CM");
        cm.setUrl(UtilsUrl.parseUrl(host + ":" + Integer.toString(port)));
    }

    @CliCommand(value = "list-ep", help = "Lists all running event processing instances.")
    public String listEP() {

        String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

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

    @CliCommand(value = "list-dev", help = "Lists all running devices")
    public String listDev() {
        String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

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
