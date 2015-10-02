package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import event.processing.query.Query;
import event.processing.query.model.AggregateCondition;

public class EPLBuilderMultiple extends EPLBuilder {

    /**
     * insert into AggregatedValue select [aggregated_value] as value from DataSource[window] as d where [where_condition] [where_domain]
     */
    private static final String EPL_INSERT_PATTERN = new String("insert into AggregatedValue select " + EPLBuilder.PART_AGGREGATED_OPERATION + " as value from DataSource" + EPLBuilder.PART_WINDOW
            + " as d where " + EPLBuilder.PART_WHERE_CONDITION + EPLBuilder.PART_WHERE_DOMAIN);

    /**
     * select * from AggregatedValue where [aggregated_operation]
     */
    private static final String EPL_SELECT_PATTERN = new String("select * from AggregatedValue where " + EPLBuilder.PART_AGGREGATED_VALUE);

    private AggregateCondition condition;

    public EPLBuilderMultiple(Query query) {
        super(query);
    }

    @Override
    public List<String> createEPL(Query query) {

        this.query = query;

        List<String> epls = new ArrayList<String>();

        for (AggregateCondition condition : query.collectAggregateCondition()) {

            this.condition = condition;

            String eplInsert = new String(EPL_INSERT_PATTERN);
            eplInsert = StringUtils.replace(eplInsert, EPLBuilder.PART_WHERE_CONDITION, this.createEPLPartWhereCondition());
            eplInsert = StringUtils.replace(eplInsert, EPLBuilder.PART_WHERE_DOMAIN, this.createEPLPartWhereDomain());
            eplInsert = StringUtils.replace(eplInsert, EPLBuilder.PART_WINDOW, this.createEPLPartWindow());
            eplInsert = StringUtils.replace(eplInsert, EPLBuilder.PART_AGGREGATED_OPERATION, this.createEPLPartAggregatedOperation());

            String eplSelect = new String(EPL_SELECT_PATTERN);
            eplSelect = StringUtils.replace(eplSelect, EPLBuilder.PART_AGGREGATED_VALUE, this.createEPLPartAggregatedValue());

            epls.add(this.cleanup(eplInsert));
            epls.add(this.cleanup(eplSelect));

            break;
        }
        return epls;
    }

    private String createEPLPartAggregatedOperation() {

        StringBuilder sb = new StringBuilder();
        sb.append(condition.getAggregation().getFunction());
        sb.append("(");
        sb.append(Query.addPrefix(condition.getProperty()));
        sb.append(")");

        return sb.toString();

    }

    private String createEPLPartAggregatedValue() {

        StringBuilder sb = new StringBuilder();
        sb.append("value");
        sb.append(" ");
        sb.append(condition.getOperator().getFunction());
        sb.append(" ");
        sb.append(condition.getValue());

        return sb.toString();

    }

    @Override
    public String createEPLPartWhereCondition() {

        String whereCondition = query.getCondition().generateInclPrefix();

        whereCondition = whereCondition.replace(condition.generateInclPrefix(), "");
        whereCondition = whereCondition.trim();

        if (whereCondition.startsWith("AND")) {
            whereCondition = whereCondition.replaceAll("^AND", "");
        } else if (whereCondition.endsWith("AND")) {
            whereCondition = whereCondition.replaceAll("AND$", "");
        }

        return whereCondition;
    }

    private String cleanup(String string) {

        if (null == string) {
            return null;
        }

        string = string.trim();
        if (string.endsWith("where")) {
            string = StringUtils.replace(string, "where", "");
        }

        return string;
    }

}
