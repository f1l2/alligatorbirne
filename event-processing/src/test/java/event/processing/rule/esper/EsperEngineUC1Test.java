package event.processing.rule.esper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.utilities.RepoUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineUC1Test extends AbstractTestEP {

    private final String query1Str = "CONDITION name = 'doorClosed' AND value=1 FROM OfficeRoom";
    private final String query2Str = "CONDITION name = 'isMovement' AND value=1 FROM OfficeRoom";

    private final String rule1Str = "UC1Q1 -> UC1Q2 TRIGGERS Heating, OfficeRoom, ON_OFF = 1 WIN:TIME(1)";
    private final String rule2Str = "UC1Q1 -> UC1Q2 TRIGGERS Heating, OfficeRoom, ON_OFF = 0";

    @Test
    public void test1() throws Exception {

        dd1 = generateTestDeviceData(1l, "doorclosed", 1l, "officeroom", 1);
        dd2 = generateTestDeviceData(2l, "ismovement", 2l, "officeroom", 1);
        dd3 = generateTestDeviceData(3l, "foo", 3l, "foo");

        qr.save(qf.parse(query1Str, "UC1Q1"));
        qr.save(qf.parse(query2Str, "UC1Q2"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule1Str), qr));
        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd3, dd2, dd1, dd2, dd1 }, new int[] { 0, 0, 1, 1, 2, 2 }, 300);

    }

    @Test
    public void test2() throws Exception {

        qr.save(qf.parse(query1Str, "occupied"));
        qr.save(qf.parse(query2Str, "notOccupied"));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(rule2Str), qr));

        engine.register(epls, testListener);

    }

    @Test
    public void test() throws Exception {

        String string = "select * from pattern [every (a=Event(name = 'occupied') -> b=Event(name = 'notOccupied')) where timer:within(1 seconds)]";

        engine.register(string, testListener);

    }

}
