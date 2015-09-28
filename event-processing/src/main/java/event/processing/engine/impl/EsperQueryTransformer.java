package event.processing.engine.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@Component
public class EsperQueryTransformer extends QueryTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperQueryTransformer.class);

    private String eql = new String("select d.device as device, d.domain as domain from DataSource as d [where search_conditions]");

    @Override
    public String transform(String in) {

        logger.debug("Start query transformation. Input: {}", in);

        QueryFactory queryFactory = new QueryFactory();

        try {
            Query query = queryFactory.parse(in);

            eql = eql.replace("[where search_conditions]", "where " + query.getCondition().replace(Query.KEYWORD_CONDITION, "").trim());

            eql = eql.replaceAll("DeviceInformation", "device");
            eql = eql.replaceAll("DomainInformation", "domain");

            removeUnused();

            logger.debug("Query transformation finished.");
            logger.info("Generated EQL: {}", eql);

            return eql;

        } catch (IOException e) {
            logger.error("Error transforming query to eql.2", e);
        }

        return null;
    }

    private void removeUnused() {

        eql = eql.replace("[where search_conditions]", "");
    }
}
