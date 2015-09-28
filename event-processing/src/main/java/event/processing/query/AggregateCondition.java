package event.processing.query;

import event.processing.query.Query.AGGREGATOR;
import event.processing.query.Query.OPERATOR;

public class AggregateCondition extends Condition {

    private OPERATOR operator;

    private AGGREGATOR aggregator;

    private String aggregateCondition;

    private String aggregateOperation;

    public OPERATOR getOperator() {
        return operator;
    }

    public void setOperator(OPERATOR operator) {
        this.operator = operator;
    }

    public AGGREGATOR getAggregator() {
        return aggregator;
    }

    public void setAggregator(AGGREGATOR aggregator) {
        this.aggregator = aggregator;
    }

    public String getAggregateCondition() {
        return aggregateCondition;
    }

    public void setAggregateCondition(String aggregateCondition) {
        this.aggregateCondition = aggregateCondition;
    }

    public String getAggregateOperation() {
        return aggregateOperation;
    }

    public void setAggregateOperation(String aggregateOperation) {
        this.aggregateOperation = aggregateOperation;
    }

    public void generate() {

    }

}
