package event.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import common.component.ApplicationStartUpUtils;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
        try {
            ApplicationStartUpUtils.processLocalConnection(arg0);
        } catch (Exception e) {
            logger.error("Error retrieving local connection data.", e);
        } finally {
            status.setCurrent(STATUS_TYPE.STARTED_UP);
        }

    }
}
