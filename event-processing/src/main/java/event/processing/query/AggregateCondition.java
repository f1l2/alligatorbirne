package event.processing.query;

import event.processing.query.Query.AGGREGATION_FUNCTION;
import event.processing.query.Query.COMPARE_FUNCTION;

public class AggregateCondition {

    private COMPARE_FUNCTION operator;

    private AGGREGATION_FUNCTION aggregator;

    private String aggregateCondition;

    private String aggregateOperation;

    public COMPARE_FUNCTION getOperator() {
        return operator;
    }

    public void setOperator(COMPARE_FUNCTION operator) {
        this.operator = operator;
    }

    public AGGREGATION_FUNCTION getAggregator() {
        return aggregator;
    }

    public void setAggregator(AGGREGATION_FUNCTION aggregator) {
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

    public String generate() {

        return "";
    }

}
