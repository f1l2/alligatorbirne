package common.lan.query.model;

public class SingleCondition extends Condition {

    private Evaluation evaluation = null;

    private AggregateCondition aggregateCondition = null;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public AggregateCondition getAggregateCondition() {
        return aggregateCondition;
    }

    public void setAggregateCondition(AggregateCondition aggregateCondition) {
        this.aggregateCondition = aggregateCondition;
    }

    public String generate() {

        if (null != evaluation) {
            return evaluation.generate();
        } else {
            return aggregateCondition.generate();
        }
    }

    public String generateInclPrefix() {
        if (null != evaluation) {
            return evaluation.generateInclPrefix();
        } else {
            return aggregateCondition.generateInclPrefix();
        }
    }
}
