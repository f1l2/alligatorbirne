package event.processing.engine.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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

    private String eql = new String("select d.deviceInformation as device, d.domainInformation as domain from DataSource as d where [where_condition] [where_domainInformation]");

    @Override
    public String transform(String in) {

        logger.debug("Start query transformation. Input: {}", in);

        QueryFactory queryFactory = new QueryFactory();

        try {
            Query query = queryFactory.parse(in);

            if (null != query.getCondition()) {

                String where_condition;

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

                }
            }

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
        eql = StringUtils.replace(eql, "[where_domainInformation]", "");
    }
}
