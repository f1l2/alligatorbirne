package event.processing.query.esper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import common.data.model.SensorData;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.repo.QueryRepository;
import event.processing.statement.QueryLangFactory;
import event.processing.statement.RuleLangFactory;
import event.processing.utilities.RepoUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineRuleTest extends AbstractTestEP {

    @Autowired
    private QueryLangFactory qf;

    @Autowired
    private QueryRepository qr;

    @Autowired
    private RuleLangFactory rf;

    @Test
    public void test1() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";

        final String rule1Str = "name1 -> name2 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 1, 1 });

    }

    @Test
    public void test2() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";

        final String rule1Str = "name2 -> name1 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 0 });

    }

    @Test
    public void test3() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";
        final String query3Str = "CONDITION id = 3";

        final String rule1Str = "name1 -> name2 -> name3 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));
        qr.save(qf.parse(query3Str, "name3"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 1 });

    }

    @Test
    public void test4() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";

        final String rule1Str = "name1 -> name2 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd1, dd2 }, new int[] { 0, 1, 1, 2 });

    }

    @Test
    public void test5() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";

        final String rule1Str = "name1 -> name2 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd2, dd2 }, new int[] { 0, 1, 1, 1 });

    }

    @Test
    public void test6() throws Exception {

        final String query1Str = "CONDITION id = 1";
        final String query2Str = "CONDITION id = 2";

        final String rule1Str = "name1 -> name2 TRIGGERS device1, domain1, propterty = 1";

        qr.save(qf.parse(query1Str, "name1"));
        qr.save(qf.parse(query2Str, "name2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd1, dd1, dd3, dd2, dd2, dd1, dd2 }, new int[] { 0, 0, 0, 0, 1, 1, 1, 2 });

    }

    @Test
    public void test7() throws Exception {

        SensorData<Integer> sd1 = new SensorData<Integer>();
        sd1.setRawValue(10);

        SensorData<Integer> sd2 = new SensorData<Integer>();
        sd2.setRawValue(20);

        SensorData<Integer> sd3 = new SensorData<Integer>();
        sd3.setRawValue(30);

        DeviceData devd1 = generateTestDeviceData(1l, "temperature", 1l, "domain1");
        devd1.setSensorData(sd1);

        DeviceData devd2 = generateTestDeviceData(2l, "temperature", 2l, "domain2");
        devd2.setSensorData(sd2);

        DeviceData devd3 = generateTestDeviceData(3l, "temperature", 3l, "domain3");
        devd3.setSensorData(sd3);

        final String query1Str = "CONDITION name = 'temperature' AND value >= 25";
        final String query2Str = "CONDITION name = 'temperature' AND value <= 15";

        final String rule1Str = "detectTemperatureUnder15 -> detectTemperatureAbove25 TRIGGERS Temperature, FLOOR1, TASK_INTERVAL_MS = 2000";

        final String rule2Str = "detectTemperatureAbove25 -> detectTemperatureUnder15 TRIGGERS Temperature, FLOOR1, TASK_INTERVAL_MS = 10000";

        qr.save(qf.parse(query1Str, "detectTemperatureAbove25"));
        qr.save(qf.parse(query2Str, "detectTemperatureUnder15"));

        List<String> epls1 = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));
        List<String> epls2 = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule2Str), qr));

        engine.register(epls1, testListener);
        engine.register(epls2.get(2), testListener);

        epls1.forEach(item -> System.out.println(item));
        System.out.println(epls2.get(2));

        sendEventAndWait(new DeviceData[] { devd1, devd3, devd3, devd3, devd1, devd1, devd3, devd1, devd1, devd1 }, new int[] { 0, 1, 1, 1, 2, 2, 3, 4, 4, 4 });

    }
}
