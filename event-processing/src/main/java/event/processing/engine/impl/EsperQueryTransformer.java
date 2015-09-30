package event.processing.engine.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.query.model.AggregateCondition;
import event.processing.query.model.CompositeCondition;
import event.processing.query.model.Condition;
import event.processing.query.model.SingleCondition;

@Component
public class EsperQueryTransformer extends QueryTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperQueryTransformer.class);

    private static final String EQL_PATTERN = "select * from DataSource[window] as d where [where_condition] [where_domain]";

    private static final String EQL_INSERT_PATTERN = new String(
            "insert into AggregatedValue select [aggregate_operation] as value from DataSource[window] as d where [where_condition] [where_domain]");

    private static final String EQL_SELECT_PATTERN = new String("select * from AggregatedValue where value [operator] [value]");

    @Override
    public List<String> transform(Query query) {

        List<String> queries = new ArrayList<String>();
        List<AggregateCondition> aConditions = null;

        String eql = null;

        String insert = null;

        String select = null;

        if (null == query) {
            queries.add(EQL_PATTERN);
            return queries;
        }

        if (null != query.getCondition()) {

            aConditions = collectAggregateCondition(query.getCondition());
            if (!CollectionUtils.isEmpty(aConditions)) {

                for (AggregateCondition aCondition : aConditions) {

                    insert = new String(EQL_INSERT_PATTERN);
                    insert = StringUtils.replace(insert, "[aggregate_operation]", aCondition.getAggregation().getFunction() + "(" + Query.addPrefix(aCondition.getProperty()) + ")");

                    String whereCondition = query.getCondition().generateInclPrefix();
                    whereCondition = whereCondition.replace(aCondition.generateInclPrefix(), "");
                    whereCondition = whereCondition.trim();

                    if (whereCondition.startsWith("AND")) {
                        whereCondition = whereCondition.replaceAll("^AND", "");
                    } else if (whereCondition.endsWith("AND")) {
                        whereCondition = whereCondition.replaceAll("AND$", "");
                    }

                    insert = StringUtils.replace(insert, "[where_condition]", whereCondition);

                    select = new String(EQL_SELECT_PATTERN);
                    select = StringUtils.replace(select, "[operator]", aCondition.getOperator().getFunction());
                    select = StringUtils.replace(select, "[value]", aCondition.getValue());

                    break;
                }
            } else {
                eql = new String(EQL_PATTERN);
                eql = StringUtils.replace(eql, "[where_condition]", query.getCondition().generateInclPrefix());
            }
        }

        if (!CollectionUtils.isEmpty(query.getDomains())) {
            StringBuilder sb = new StringBuilder();
            sb.append("AND ");
            sb.append("( ");
            sb.append(query.getDomains().stream().map(item -> "d.domainInformation.name = '".concat(item).concat("'")).collect(Collectors.joining(" AND ")));
            sb.append(" )");

            eql = StringUtils.replace(eql, "[where_domain]", sb.toString());
            insert = StringUtils.replace(insert, "[where_domain]", sb.toString());
        }

        if (null != query.getWindow()) {
            eql = StringUtils.replace(eql, "[window]", query.getWindow().generate());
            insert = StringUtils.replace(insert, "[window]", query.getWindow().generate());
        }

        if (!CollectionUtils.isEmpty(aConditions)) {
            select = cleanup(select);
            insert = cleanup(insert);
            queries.add(insert);
            queries.add(select);
        } else {
            eql = cleanup(eql);
            queries.add(eql);
        }

        logger.debug("Query transformation finished.");

        queries.forEach(item -> logger.info("Generated EQL: {}", item));

        return queries;
    }

    @Override
    public List<String> transform(String in) {

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

    private List<AggregateCondition> collectAggregateCondition(Condition condition) {

        List<AggregateCondition> aConditions = new ArrayList<AggregateCondition>();
        if (condition instanceof SingleCondition) {
            collectAggregateCondition((SingleCondition) condition, aConditions);
        } else if (condition instanceof CompositeCondition) {
            collectAggregateCondition((CompositeCondition) condition, aConditions);
        }
        return aConditions;
    }

    private void collectAggregateCondition(CompositeCondition condition, List<AggregateCondition> collection) {
        if (condition.getCc() != null)
            collectAggregateCondition(condition.getCc(), collection);
        if (condition.getSc() != null)
            collectAggregateCondition(condition.getSc(), collection);
    }

    private void collectAggregateCondition(SingleCondition condition, List<AggregateCondition> collection) {

        if (condition.getAggregateCondition() != null) {
            collection.add(condition.getAggregateCondition());
        }
    }

    private String cleanup(String string) {

        if (null == string) {
            return string;
        }

        string = StringUtils.replace(string, "[select *]", "select *");
        string = StringUtils.replace(string, "[where_condition]", "");
        string = StringUtils.replace(string, "[where_domain]", "");
        string = StringUtils.replace(string, "[window]", "");
        string = string.trim();

        if (string.endsWith("where")) {
            string = StringUtils.replace(string, "where", "");
        }

        return string;

    }
}
