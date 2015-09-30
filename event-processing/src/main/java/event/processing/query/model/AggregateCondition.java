package event.processing.query.model;

import event.processing.query.Query;
import event.processing.query.Query.AGGREGATION_FUNCTION;
import event.processing.query.Query.COMPARE_FUNCTION;

public class AggregateCondition {

    private COMPARE_FUNCTION operator;

    private AGGREGATION_FUNCTION aggregation;

    private String aggregateCondition;

    private String property;

    private String value;

    public COMPARE_FUNCTION getOperator() {
        return operator;
    }

    public void setOperator(COMPARE_FUNCTION operator) {
        this.operator = operator;
    }

    public String getAggregateCondition() {
        return aggregateCondition;
    }

    public void setAggregateCondition(String aggregateCondition) {
        this.aggregateCondition = aggregateCondition;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AGGREGATION_FUNCTION getAggregation() {
        return aggregation;
    }

    public void setAggregation(AGGREGATION_FUNCTION aggregation) {
        this.aggregation = aggregation;
    }

    public String generate() {

        StringBuilder sb = new StringBuilder();
        sb.append(aggregation.getFunction());
        sb.append("(");
        sb.append(property);
        sb.append(")");
        sb.append(" ");
        sb.append(operator.getFunction());
        sb.append(" ");
        sb.append(value);

        return sb.toString();
    }

    public String generateInclPrefix() {

        StringBuilder sb = new StringBuilder();
        sb.append(aggregation.getFunction());
        sb.append("(");
        sb.append(Query.addPrefix(property));
        sb.append(")");
        sb.append(" ");
        sb.append(operator.getFunction());
        sb.append(" ");
        sb.append(value);

        return sb.toString();
    }
}
