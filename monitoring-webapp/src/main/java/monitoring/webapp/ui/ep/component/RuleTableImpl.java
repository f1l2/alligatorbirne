package monitoring.webapp.ui.ep.component;

import common.data.dto.RuleDTO;
import monitoring.webapp.ui.table.component.BeanItemColumnGenerator;
import monitoring.webapp.ui.table.component.BeanItemTableImpl;

@SuppressWarnings("serial")
public class RuleTableImpl extends BeanItemTableImpl<RuleDTO, RuleTable.COLUMN>implements RuleTable {

    public RuleTableImpl() {

        super(RuleDTO.class);

        addColumn(COLUMN.NAME, new BeanItemColumnGenerator<RuleDTO>() {

            @Override
            public Object generateCell(RuleDTO beanItem) {
                return beanItem.getName();
            }

        });

        addColumn(COLUMN.RULE, new BeanItemColumnGenerator<RuleDTO>() {
            @Override
            public Object generateCell(RuleDTO beanItem) {
                return beanItem.getRule();

            }
        });

        addColumn(COLUMN.ACTIVE, new BeanItemColumnGenerator<RuleDTO>() {

            @Override
            public Object generateCell(RuleDTO beanItem) {

                if (beanItem.getIsActive()) {
                    return "Yes";
                }
                return "No";

            }

        });
    }

}
