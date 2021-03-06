package event.processing.query.esper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import event.processing.AbstractTestEP;
import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineUC5Test extends AbstractTestEP {

    @Test
    public void test1() throws IOException {

        String query = "CONDITION id = 1 WIN:LENGTH(1)";

        engine.register(transformQueryString(query, "query"), testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 1, 1, 1 });

    }

    @Test
    public void test2() throws IOException {

        String query = "CONDITION SUM(id) >= 4 WIN:LENGTH(1)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 0 });
    }

    @Test
    public void test3() throws IOException {

        String query = "CONDITION SUM(id) >= 4 WIN:LENGTH(2)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 1 });
    }

    @Test
    public void test4() throws IOException {

        String query = "CONDITION MIN(id) < 2 WIN:LENGTH(2)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 1, 2, 2 });
    }

    @Test
    public void test5() throws IOException {

        String query = "CONDITION COUNT(id) = 3 WIN:LENGTH(2)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 0 });
    }

    @Test
    public void test6() throws IOException {

        String query = "CONDITION COUNT(id) = 3 WIN:LENGTH(3)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 1 });
    }

    @Test
    public void test7() throws IOException {
        String query = "CONDITION SUM(id) >= 3 WIN:LENGTH(2)";
        engine.register(transformQueryString(query, "query"), testListener);
        sendEventAndWait(new DeviceData[] { dd3, dd1, dd1, dd2, dd2 }, new int[] { 1, 2, 2, 3, 4 });
    }

}
