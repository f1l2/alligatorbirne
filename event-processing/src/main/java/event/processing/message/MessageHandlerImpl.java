package event.processing.message;

import java.util.List;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import common.data.dto.DeviceDataDTO;
import common.data.model.DeviceData;
import common.transformer.DeviceDataTransformer;

@Service
public class MessageHandlerImpl implements MessageHandler<DeviceDataDTO> {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerImpl.class);

    private ActiveMQConnectionFactory connectionFactory = null;

    private Connection connection = null;

    private Session session = null;

    @Override
    public synchronized void start(common.data.Connection mb) {
        try {
            connectionFactory = new ActiveMQConnectionFactory("tcp://" + mb.getUrl().getAuthority());

            connection = connectionFactory.createConnection();

            connection.start();

        } catch (Exception e) {
            logger.error("Error starting message handler: {}", e);
        }
    }

    @Override
    public synchronized void stop() {
        try {
            session.close();
            connection.close();
        } catch (Exception e) {
            logger.error("Error stopping message handler: ", e);
        }
    }

    @Override
    public synchronized void produce(DeviceDataDTO deviceDataDTO) {

        try {
            if (null == session) {
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }

            Topic topic = session.createTopic(MESSAGE_TOPIC);

            MessageProducer producer = session.createProducer(topic);

            List<DeviceData> deviceData = DeviceDataTransformer.fork(deviceDataDTO);

            for (DeviceData dd : deviceData) {

                ObjectMessage message = session.createObjectMessage(dd);

                String propertyValue = dd.getDevice().getName() + ":" + dd.getDomain().getName();

                System.out.println(propertyValue);

                message.setStringProperty(PROPERTY, propertyValue);

                producer.send(message);
            }

        } catch (Exception e) {
            logger.error("Error sending device data: {}", e);
        }
    }

    @Override
    public synchronized void consume(String selector, MessageListener messageListener) {
        try {

            if (null != session) {
                session.close();
            }

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(MESSAGE_TOPIC);

            MessageConsumer consumer;
            if (selector != null) {
                consumer = session.createConsumer(topic, selector);
            } else {
                consumer = session.createConsumer(topic);
            }
            consumer.setMessageListener(messageListener);

        } catch (Exception e) {
            logger.error("Error installing message consumer: {}", e);
        }
    }
}
