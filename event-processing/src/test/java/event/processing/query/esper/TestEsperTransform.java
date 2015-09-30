package event.processing.query.esper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.engine.impl.EsperEngineListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestEsperTransform extends AbstractTestEP {

    final static Logger logger = LoggerFactory.getLogger(TestEsperTransform.class);

    private static String input;

    @Test
    public void testEsperEngineUC1() throws IOException {

        /**
         * CONDITION id = 5
         */

        input = "CONDITION id = 5";
        transformAndRegister(input);

    }

    @Test
    public void testEsperEngineUC2() {

        /**
         * CONDITION id = 10 AND name = 'foo'
         */

        input = "CONDITION id = 10 AND name = 'foo'";
        transformAndRegister(input);
    }

    @Test

    /**
     * CONDITION SUM(id) >= 5
     */

    public void testEsperEngineUC3() {

        /**
         * CONDITION SUM(id) >= 5
         *
         *
         * 
         * String query = "insert into AggregatedValue select sum(DeviceInformation.id) as value from DeviceInformation";
         * 
         * 
         * String query2 = "select * from DataSourceAgg where value > 5";
         *
         */

        input = "CONDITION SUM(id) >= 5";
        transformAndRegister(input);
    }

    @Test
    public void testEsperEngineUC4() {

        /**
         * CONDITION property >= 10 FROM name_of_domain
         */

        input = "CONDITION id >= 10 FROM name_of_domain";
        transformAndRegister(input);
    }

    @Test
    public void testEsperEngineUC5() {
        /**
         * CONDITION SUM (property) >= 10 From domain WIN:LENGTH(10)
         */

        input = "CONDITION SUM (property) >= 10 From domain WIN:LENGTH(10)";
        transformAndRegister(input);

    }

    public void testEsperEngine2() {

        // String query1 = "select d.device as device from DataSource as d where device.id = 5";
        //
        // register(query1);
        //
        // String query2 = "select d.device as device from DataSource as d where device.id = 5 and device.id = 20";
        //
        // register(query2);
        //
        // String query31 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource as d";
        //
        // String query32 = "select * from DataSourceAgg where value > 5";
        //
        // register(query31);
        //
        // register(query32);
        //
        // String query4 = "select d.device as device, d.domain as domain from DataSource d where device.id > 10 AND domain.name = 'name'";
        //
        // register(query4);
        //
        // String query51 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource.win:time(30) as d;select * from DataSourceAgg where value > 5;
        //
        // String query52 = "select * from DataSourceAgg where value > 5";
        //
        // register(query51);
        //
        // register(query52);
    }

    @Test
    public void testEsperEngine3() {

        String query = "insert into AggregatedValue select sum(d.deviceInformation.id) as value, 12 as id from DataSource.win:time(10) as d where d.deviceInformation.name = 'hallo'";
        String query2 = "select * from AggregatedValue where value > 5 and id = 12";

        engine.registerQuery(query, new EsperEngineListener());
        engine.registerQuery(query2, testListener);

    }

    private void transformAndRegister(String query) {

        logger.info(String.format("Transform query: %s", query));

        String eql = queryTransformer.transform(query).get(0);

        engine.registerQuery(eql, testListener);
    }
}
