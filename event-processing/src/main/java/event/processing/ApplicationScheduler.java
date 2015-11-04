package event.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Component
public class ApplicationScheduler {

    final static Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    private static Connection local, cm;

    public static long id = -1;

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
                local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
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

                String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING, cm);
                logger.info(url);

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, local, Connection.class);
                local = responseRegistration.getBody();
                id = local.getId();

                logger.info("EP registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + local);

                status.setCurrent(STATUS_TYPE.WORKING);

            } catch (Exception ex) {
                logger.error("Register error. Exception: {}", ex.getMessage());
            }

            break;

        case WORKING:

            logger.info("Send heart beat");

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING, cm);
            url = url.replace("{id}", Long.toString(local.getId()));

            restTemplate.put(url, null);

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}
