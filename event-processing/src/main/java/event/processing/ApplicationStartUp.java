package event.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.component.ApplicationStartUpUtils;
import common.data.setting.SettingUtils;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent iEvent) {

        try {
            String baseDirectory = ApplicationStartUpUtils.getBaseDirectory(Application.class);
            SettingUtils.setPATH_TO_SETTING_FILE(baseDirectory + Application.RELATIVE_PATH_TO_CONFIG);
            ApplicationStartUpUtils.processLocalConnection(iEvent);

        } catch (Exception e) {
            logger.error("Error retrieving local connection data.", e);
            status.setCurrent(STATUS_TYPE.ERROR);
        } finally {
            status.setCurrent(STATUS_TYPE.STARTED_UP);
        }

    }
}
