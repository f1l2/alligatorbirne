package event.processing.query.esper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.query.Query;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestEsperTransform extends AbstractTestEP {

    final static Logger logger = LoggerFactory.getLogger(TestEsperTransform.class);

    private static String query, eql;

    @Test
    public void testEsperEngineUC1() {

        query = Query.KEYWORD.CONDITION.getKeyword() + " id = 5";

        eql = factory.getQueryTransformer().transform(query);

        transformAndRegister(query);

    }

    @Test
    public void testEsperEngineUC2() {

        query = Query.KEYWORD.CONDITION.getKeyword() + " id = 10  " + Query.LOGIC_SYMBOL.AND.getSymbol() + " name = 'abc'";

        transformAndRegister(query);

    }

    @Test
    public void testEsperEngineUC3() {
        query = Query.KEYWORD.CONDITION.getKeyword() + " " + Query.AGGREGATOR.SUM.getSign() + "(value)" + Query.OPERATOR.IS_GREATER + " 5 " + Query.KEYWORD.FROM.getKeyword();

        transformAndRegister(query);
    }

    @Test
    public void testEsperEngineUC4() {

        /**
         * CONDITION property >= 10 FROM name_of_domain
         */

        query = Query.KEYWORD.CONDITION.getKeyword() + " id " + Query.OPERATOR.IS_GREATER.getSign() + " 10 " + Query.KEYWORD.FROM.getKeyword() + " name_of_domain";

        assertEquals("", "");

        transformAndRegister(query);
    }

    @Test
    public void testEsperEngineUC5() {
        /**
         * CONDITION SUM (property) >= 10 From domain WIN.TIME(10)
         */

    }

    @Test
    public void testEsperEngine2() {

        String query1 = "select d.device as device from DataSource as d where device.id = 5";

        register(query1);

        String query2 = "select d.device as device from DataSource as d where device.id = 5 and device.id = 20";

        register(query2);

        String query31 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource as d";

        String query32 = "select * from DataSourceAgg where value > 5";

        register(query31);

        register(query32);

        String query4 = "select d.device as device, d.domain as domain from DataSource d where device.id > 10 AND domain.name = 'name'";

        register(query4);

        String query51 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource.win:time(30) as d";

        String query52 = "select * from DataSourceAgg where value > 5";

        register(query51);

        register(query52);
    }

    @Test
    public void testEsperEngine3() {

        String query = "insert into DeviceInformationAgg select sum(DeviceInformation.id) as value from DeviceInformation";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg where value > 5";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg, DeviceInformation";

        engine.registerQuery(query, null);

        test("");

    }

    private void register(String eql) {
        engine.registerQuery(eql, factory.getEngineListener());
    }

    private void transformAndRegister(String query) {

        logger.info(String.format("Transform query: %s", query));

        String eql = queryTransformer.transform(query);

        engine.registerQuery(eql, listener);
    }

    private void test(String eql) {

        engine.registerQuery(eql, listener);
        delay(1000);

        sendEventAndWait(dataSource1, 1000);

        sendEventAndWait(dataSource2, 1000);

        sendEventAndWait(dataSource3, 1000);
    }
}
