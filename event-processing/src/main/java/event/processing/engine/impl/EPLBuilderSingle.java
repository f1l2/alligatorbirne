package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import event.processing.query.Query;

public class EPLBuilderSingle extends EPLBuilder {

    private static final String EPL_PATTERN = "select * from DeviceData[window] as d where [where_condition] [where_domain]";

    public EPLBuilderSingle(Query query) {
        super(query);
    }

    @Override
    public List<String> createEPL(Query query) {

        this.query = query;

        String epl = new String(EPL_PATTERN);

        epl = StringUtils.replace(epl, EPLBuilder.PART_WHERE_CONDITION, this.createEPLPartWhereCondition());
        epl = StringUtils.replace(epl, EPLBuilder.PART_WHERE_DOMAIN, this.createEPLPartWhereDomain());
        epl = StringUtils.replace(epl, EPLBuilder.PART_WINDOW, this.createEPLPartWindow());

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
