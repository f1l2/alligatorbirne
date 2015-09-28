package event.processing.engine.impl;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@Component
public class EsperQueryTransformer extends QueryTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperQueryTransformer.class);

    private String eql = new String("select d.deviceInformation as device, d.domainInformation as domain from DataSource as d where [where_deviceInformation] [where_domainInformation]");

    @Override
    public String transform(String in) {

        logger.debug("Start query transformation. Input: {}", in);

        QueryFactory queryFactory = new QueryFactory();

        try {
            Query query = queryFactory.parse(in);

            /**
             * Convert domain part
             */

            if (!CollectionUtils.isEmpty(query.getDomains())) {

                StringBuilder sb = new StringBuilder();
                sb.append(" AND ");
                sb.append(query.getDomains().stream().map(item -> "d.domainInformation.name = '".concat(item).concat("'")).collect(Collectors.joining(" AND ")));

                eql = eql.replace("[where_domainInformation]", sb.toString());
            }

            /**
             * Convert condition part
             */
            if (!CollectionUtils.isEmpty(query.getLogicLinks())) {

            } else if (!CollectionUtils.isEmpty(query.getAggregates())) {

            } else {

            }

            // if (!CollectionUtils.isEmpty(query.getAggregateCompares())) {
            //
            // } else if (!CollectionUtils.isEmpty(query.getLogicLinks())) {
            //
            // eql = StringUtils.replace(eql, "[where]", "where");
            //
            // for (int i = 0; i < query.getLogicLinks().size(); i++) {
            //
            // }
            //
            // } else {
            //
            // }
            //
            // if (!CollectionUtils.isEmpty(query.getCompare())) {
            //
            // String where_deviceInformation = query.getCompareLogic();
            //
            // for (int i = 0; i < query.getCompare().size(); i++) {
            //
            // String compare = query.getCompare().get(i);
            //
            // String property1 = query.getProperty().get(2 * i);
            // if (isPropertyVariable(property1)) {
            // compare = compare.replace(property1, "d.deviceInformation." + property1);
            // }
            //
            // String property2 = query.getProperty().get(2 * i + 1);
            // if (isPropertyVariable(property2)) {
            // compare = compare.replace(property2, "d.deviceInformation." + property2);
            // }
            //
            // where_deviceInformation = where_deviceInformation.replace(query.getCompare().get(i), compare);
            //
            // }
            //
            // eql = StringUtils.replace(eql, "[where_deviceInformation]", where_deviceInformation);
            // }
            //
            // if (!CollectionUtils.isEmpty(query.getDomain())) {
            //
            // eql = StringUtils.replace(eql, "[where]", "where");
            //
            // StringBuilder sb = new StringBuilder();
            // sb.append(" AND ");
            // sb.append(query.getDomain().stream().map(item -> "d.domainInformation.name = '".concat(item).concat("'")).collect(Collectors.joining(" AND ")));
            //
            // eql = eql.replace("[where_domainInformation]", sb.toString());
            //
            // }
            //
            // if (!StringUtils.isEmpty(query.getWindow())) {
            //
            // }
            //
            // if (!CollectionUtils.isEmpty(query.getAggregate())) {
            //
            // /**
            // * insert into TEMP select sum(d.device.property) as value from DataSource d
            // *
            // * select * from TEMP where value > 5
            // */
            //
            // for (String aggregate : query.getAggregate()) {
            // System.out.println(aggregate);
            // }
            //
            // }

            removeUnused();

            logger.debug("Query transformation finished.");
            logger.info("Generated EQL: {}", eql);

            return eql;

        } catch (

        IOException e)

        {
            logger.error("Error transforming query to eql.2", e);
        }

        return null;

    }

    private Boolean isPropertyVariable(String property) {
        return !(property.matches("^[0-9]+$") || (property.matches("^'.+'$")));
    }

    private void removeUnused() {

        eql = StringUtils.replace(eql, "[where_deviceInformation]", "");
        eql = StringUtils.replace(eql, "[where_domainInformation]", "");
    }
}
