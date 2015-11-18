package event.processing.messaging;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import common.data.model.DeviceData;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.engine.EngineFactory;

@Service
public class MessageHandlerListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerListener.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Override
    public void onMessage(Message item) {

        try {
            logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EP_SEND));

            ActiveMQObjectMessage message = (ActiveMQObjectMessage) item;
            DeviceData deviceData = (DeviceData) message.getObject();

            logger.info(deviceData.toString());
            factory.getEngine().send(deviceData);

        } catch (Exception e) {
            logger.error("Error consuming device data: {}", e);
        }
    }

}
