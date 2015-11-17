package monitoring.webapp.ui.ep.component;

import common.data.dto.QueryDTO;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;

@SuppressWarnings("serial")
public class QueryTableImpl extends BeanItemTableImpl<QueryDTO, QueryTable.COLUMN>implements QueryTable {

    public QueryTableImpl() {

        super(QueryDTO.class);

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<QueryDTO>() {

            @Override
            public Object generateCell(QueryDTO beanItem) {
                return beanItem.getName();
            }

        });

        addColumn(COLUMN.QUERY, new BeanItemColumnGenerator<QueryDTO>() {

            public Object generateCell(QueryDTO beanItem) {
                return beanItem.getQuery();
            }
        });
    }
}
