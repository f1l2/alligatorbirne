package event.processing.engine.impl;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import event.processing.engine.QueryTransformer;
import event.processing.query.AggregateCondition;
import event.processing.query.CompositeCondition;
import event.processing.query.Evaluation;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.query.SingleCondition;

@Component
public class EsperQueryTransformer extends QueryTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperQueryTransformer.class);

    private static final String eqlPattern = "select d.deviceInformation as device, d.domainInformation as domain from DataSource as d where [where_condition] [where_domain]";

    private String eql;

    // private static String eql_insert = new String("insert into AggregatedValue select [aggregate_operation] as value, [id] as id from DataSource as d");

    // private static String eql_select = new String("select * from AggregatedValue where value > 5 and id = 12");

    @Override
    public String transform(Query query) {

        eql = new String(eqlPattern);

        if (null == query) {
            return eql;
        }

        if (null != query.getCondition()) {

            if (query.getCondition() instanceof SingleCondition) {

                SingleCondition condition = (SingleCondition) query.getCondition();

                check(condition.getEvaluation());

                eql = eql.replace("[where_condition]", condition.getEvaluation().generate());

            } else if (query.getCondition() instanceof CompositeCondition) {

                CompositeCondition condition = (CompositeCondition) query.getCondition();

                if (condition.getCompositeFunction().getNumberOperand() == 1) {
                    check(condition.getEvaluation1());
                } else if (condition.getCompositeFunction().getNumberOperand() == 2) {
                    check(condition.getEvaluation1());
                    check(condition.getEvaluation2());
                }

                eql = eql.replace("[where_condition]", condition.generate());

            } else if (query.getCondition() instanceof AggregateCondition) {

                AggregateCondition aggregateCondition = (AggregateCondition) query.getCondition();
                // TODO
            }
        }

        if (!CollectionUtils.isEmpty(query.getDomains())) {
            StringBuilder sb = new StringBuilder();
            sb.append("AND ");
            sb.append("( ");
            sb.append(query.getDomains().stream().map(item -> "d.domainInformation.name = '".concat(item).concat("'")).collect(Collectors.joining(" AND ")));
            sb.append(" )");
            eql = eql.replace("[where_domain]", sb.toString());
        }

        removeUnused();

        logger.debug("Query transformation finished.");
        logger.info("Generated EQL: {}", eql);

        return eql;
    }

    @Override
    public String transform(String in) {

        logger.info("Start query transformation. Input: {}", in);

        Query query = null;

        try {
            QueryFactory queryFactory = new QueryFactory();
            query = queryFactory.parse(in);

        } catch (IOException e) {
            logger.error("Error parsing query.", e);
        }

        return transform(query);

    }

    private void check(Evaluation evaluation) {

        if (isPropertyVariable(evaluation.getProperty1())) {
            evaluation.setProperty1("d.deviceInformation.".concat(evaluation.getProperty1()));
        }
        if (isPropertyVariable(evaluation.getProperty2())) {
            evaluation.setProperty1("d.deviceInformation.".concat(evaluation.getProperty2()));
        }

    }

    private Boolean isPropertyVariable(String property) {
        return !(property.matches("^[0-9]+$") || (property.matches("^'.+'$")));
    }

    private void removeUnused() {

        eql = StringUtils.replace(eql, "[where_condition]", "");
        eql = StringUtils.replace(eql, "[where_domain]", "");
    }
}
