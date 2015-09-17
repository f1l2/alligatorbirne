package event.processing.engine.impl;

import java.io.IOException;

import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

public class EsperQueryTransformer extends QueryTransformer {

    private String eql = new String("select d.device as device, d.domain as domain from DataSource as d [where search_conditions]");

    @Override
    public String transform(String in) {

        QueryFactory queryFactory = new QueryFactory();

        try {
            Query query = queryFactory.create(in);

            eql = eql.replace("[where search_conditions]", "where " + query.getCondition().replace(Query.KEYWORD_CONDITION, "").trim());

            eql = eql.replaceAll("DeviceInformation", "device");
            eql = eql.replaceAll("DomainInformation", "domain");

            removeUnused();

            return eql;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private void removeUnused() {

        eql = eql.replace("[where search_conditions]", "");
    }
}
