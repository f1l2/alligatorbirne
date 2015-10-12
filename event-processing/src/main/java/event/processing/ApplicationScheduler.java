package event.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.setting.SettingUtils;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Component
public class ApplicationScheduler {

    final static Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    private static Connection local, cm;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Status status;

    public void carryOutActivity() {

        switch (status.getCurrent()) {
        case STARTED_UP:

            try {
                /**
                 * Load connection data.
                 */
                local = SettingUtils.getLocalConnection();
                logger.info("Retrieve local connection data ... ");
                logger.info(local.toString());

                /**
                 * Load CM connection data
                 */
                cm = SettingUtils.getCMConnection();
                logger.info("Load CM connection data ...");
                logger.info(cm.toString());

                status.setCurrent(STATUS_TYPE.REGISTER_EP);

            } catch (Exception ex) {

                logger.error(ex.getMessage());
            }
            break;

        case REGISTER_EP:

            try {

                logger.info("Try to register ep ...");

                String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING, cm);
                logger.info(url);

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, local, Connection.class);
                local = responseRegistration.getBody();

                logger.info("EP registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + local);

            } catch (Exception ex) {
                logger.error("Register error. Exception: {}", ex.getMessage());
            }

            break;

        case WORKING:

            // TODO send heart beat
            logger.info("Send heart beat");

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}

// else if (statusRegistration == 2) {
//
// try {
// DataSources data = UtilsConfiguration.loadMeasurementData();
//
// Connection cmConnection = utilsConfig.getCMConnection();
//
// url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, cmConnection);
// url = url.replace("{id}", String.valueOf(connection.getId()));
//
// ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
// logger.info("Delegation successfully called. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");
//
// statusRegistration++;
//
// } catch (Exception ex) {
// logger.error("Error registration sources of device. Url: " + url);
// logger.error(ex);
// }
