package event.processing.message;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.bind.JAXBException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.xml.sax.SAXException;

import common.data.Connection;
import common.data.builder.DDBuilder;
import common.data.model.DeviceData;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.setting.SettingUtils;

public class AbstractTestMessageEP implements MessageListener {

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    protected MessageHandlerImpl mc;

    @Autowired
    protected MessageHandlerListener mhl;

    protected DDBuilder builder;

    protected DomainInformation domainInfo;

    protected DeviceInformation devInfo;

    protected List<DomainInformation> domains;

    protected Connection mbConnection;

    protected int count;

    @Before
    public void before() throws IOException, JAXBException, SAXException {
        builder = new DDBuilder();
        builder.buildDeviceInformation("device");

        devInfo = new DeviceInformation();
        devInfo.setName("device");

        domainInfo = new DomainInformation();
        domainInfo.setName("domain");

        domains = new ArrayList<DomainInformation>();
        domains.add(domainInfo);

        mbConnection = SettingUtils.getMBConnection();

        count = 0;

        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://" + mbConnection.getUrl().getAuthority());
            activeMQConnectionFactory.createConnection();
        } catch (JMSException e) {

            Assume.assumeTrue(false);
        }

    }

    @After
    public void after() {

    }

    @Override
    public void onMessage(Message item) {

        try {
            ActiveMQObjectMessage message = (ActiveMQObjectMessage) item;
            DeviceData deviceData = (DeviceData) message.getObject();

            assertNotNull(deviceData);
            count++;
        } catch (Exception e) {

        }
    }

}
