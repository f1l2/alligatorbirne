package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import common.lang.query.QueryLang;

public class EPLBuilderSingle extends EPLBuilder {

    private static final String EPL_PATTERN = "insert into Event select '[query_name]' as name from DeviceData[window] as d where [where_condition] [where_domain]";

    @Override
    public List<String> createEPL(List<QueryLang> queries) {

        List<String> epls = new ArrayList<String>();

        queries.stream().map(item -> epls.addAll(createEPL(item)));

        return epls;

    }

    @Override
    public List<String> createEPL(QueryLang query) {

        this.query = query;

        String epl = new String(EPL_PATTERN);

        epl = StringUtils.replace(epl, EPLBuilder.PART_WHERE_CONDITION, this.createEPLPartWhereCondition());
        epl = StringUtils.replace(epl, EPLBuilder.PART_WHERE_DOMAIN, this.createEPLPartWhereDomain());
        epl = StringUtils.replace(epl, EPLBuilder.PART_WINDOW, this.createEPLPartWindow());
        epl = StringUtils.replace(epl, EPLBuilder.PART_QUERY_NAME, query.getName());

        List<String> epls = new ArrayList<String>();
        epls.add(epl);

        return epls;
    }

    @Override
    public String createEPLPartWhereCondition() {
        if (null != query.getCondition()) {
            return query.getCondition().generateInclPrefix();
        }
        return "";
    }
}
