package iot.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.component.ApplicationStartUpUtils;
import common.data.setting.SettingUtils;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    @Autowired
    private SensorRegistry sensorRegistry;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
        try {
            String baseDirectory = ApplicationStartUpUtils.getBaseDirectory(Application.class);
            SettingUtils.setPATH_TO_SETTING_FILE(baseDirectory + Application.RELATIVE_PATH_TO_CONFIG);
            ApplicationStartUpUtils.processLocalConnection(arg0);
        } catch (Exception e) {
            logger.error("Error retrieving local connection data.", e);
        } finally {
            status.setCurrent(STATUS_TYPE.STARTED_UP);
        }

        /**
         * Instantiate sensor objects.
         * 
         * For further use, objects get addressed via class name.
         *
         **/
        sensorRegistry.instantiate();
    }

}
