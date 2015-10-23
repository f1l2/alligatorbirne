package event.processing.engine.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import event.processing.query.Query;
import event.processing.query.model.Evaluation;

public abstract class EPLBuilder {

    protected Query query;

    public static final String PART_WHERE_CONDITION = "[where_condition]";

    public static final String PART_WHERE_DOMAIN = "[where_domain]";

    public static final String PART_WINDOW = "[window]";

    public static final String PART_AGGREGATED_VALUE = "[aggregated_value]";

    public static final String PART_AGGREGATED_OPERATION = "[aggregated_operation]";

    public EPLBuilder(Query query) {
        this.query = query;
    }

    public abstract List<String> createEPL(Query query);

    public String createEPLPartWhereDomain() {

        if (!CollectionUtils.isEmpty(query.getDomains())) {
            StringBuilder sb = new StringBuilder();
            sb.append("AND (");
            sb.append(query.getDomains().stream().map(item -> Evaluation.PREFIX_DOM_INFO + "name = '".concat(item).concat("'")).collect(Collectors.joining(" AND ")));
            sb.append(" )");

            return sb.toString();
        }
        return "";
    }

    public String createEPLPartWindow() {
        if (null != query.getWindow()) {
            return query.getWindow().generate();
        }
        return "";
    }

    public abstract String createEPLPartWhereCondition();

}
