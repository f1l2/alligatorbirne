package event.processing.message;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import common.messaging.MessageHandler;
import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MessageTest extends AbstractTestMessageEP {

    @Test
    public void test1() {

        try {

            messageHandler.start(mbConnection);
            messageHandler.consume("", this);

            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.stop();

        } catch (Exception e) {

        }

        assertEquals(3, count);
    }

    @Test
    public void test2() {

        try {

            messageHandler.start(mbConnection);

            System.out.println(MessageHandler.PROPERTY + " = '" + devInfo.getName() + ":" + domainInfo.getName() + "'");

            messageHandler.consume(MessageHandler.PROPERTY + " = '" + devInfo.getName() + ":" + domainInfo.getName() + "'", this);

            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.stop();

        } catch (Exception e) {

        }

        assertEquals(3, count);
    }

    @Test
    public void test3() {

        try {

            messageHandler.start(mbConnection);
            messageHandler.consume("something", this);

            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.produce(builder.getResultDTO(domains));
            Thread.sleep(100);
            messageHandler.stop();

        } catch (Exception e) {

        }

        assertEquals(0, count);
    }

}
