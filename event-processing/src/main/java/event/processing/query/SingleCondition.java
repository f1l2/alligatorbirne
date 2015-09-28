package event.processing.query;

public class SingleCondition extends Condition {

    private Evaluation evaluation = new Evaluation();

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

}
