package event.processing.rule.esper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.UCTest;
import event.processing.utilities.RepoUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineUC2Test extends AbstractTestEP {

    @Test
    public void test1() throws Exception {

        dd1 = generateTestDeviceData(1l, "utilizationinpercent", 1l, "technicalfloor", 87);
        dd2 = generateTestDeviceData(2l, "utilizationinpercent", 2l, "technicalfloor", 90);
        dd3 = generateTestDeviceData(3l, "utilizationinpercent", 3l, "technicalfloor", 95);

        qr.save(qf.parse(UCTest.UC2_QUERY1, UCTest.UC2_NAME_QUERY1));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(UCTest.UC2_RULE1), qr));
        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 1 }, 100);

    }

    @Test
    public void test2() throws Exception {

        dd1 = generateTestDeviceData(1l, "utilizationinpercent", 1l, "technicalfloor", 85);
        dd2 = generateTestDeviceData(2l, "utilizationinpercent", 2l, "technicalfloor", 90);
        dd3 = generateTestDeviceData(3l, "utilizationinpercent", 3l, "technicalfloor", 95);

        qr.save(qf.parse(UCTest.UC2_QUERY1, UCTest.UC2_NAME_QUERY1));

        List<String> epls = this.eplTransformer.transformRule(RepoUtilities.findQueriesToQueryNames(rf.parse(UCTest.UC2_RULE1), qr));
        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 0, 0 }, 100);

    }

}
