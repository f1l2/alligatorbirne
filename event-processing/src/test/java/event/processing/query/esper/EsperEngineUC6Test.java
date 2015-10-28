package event.processing.query.esper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineUC6Test extends AbstractTestEP {

    @Autowired
    private QueryFactory qf;

    @Test
    public void test1() throws IOException {

        String query1Str = "CONDITION id = 1";
        String query2Str = "CONDITION id = 2";

        List<Query> queries = new ArrayList<Query>();
        queries.add(qf.parse(query1Str, "name1"));
        queries.add(qf.parse(query2Str, "name2"));

        List<String> epls = new ArrayList<String>();

        epls.addAll(queryTransformer.transformQuery(queries));

        epls.forEach(item -> System.out.println(item));

        if (epls.size() > 1) {

            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            int i = 0;

            StringBuilder sb = new StringBuilder();
            sb.append("select * from pattern [every ");

            for (Query query : queries) {
                if (i != 0) {
                    sb.append(" -> ");
                }
                sb.append(alphabet[i]);
                sb.append("=Event(name = '");
                sb.append(query.getName());
                sb.append("')");
                i++;
            }
            sb.append("]");

            epls.add(sb.toString());

            System.out.println(sb.toString());
        }

        engine.register(epls, testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 0, 1, 1 });

    }

}
