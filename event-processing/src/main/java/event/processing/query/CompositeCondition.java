package event.processing.query;

import event.processing.query.Query.LOGIC_FUNCTION;

public class CompositeCondition extends Condition {

    private CompositeCondition parent = null;

    private LOGIC_FUNCTION compositeFunction;

    private String compositeCondition = null;

    private Evaluation evaluation1 = new Evaluation();

    private Evaluation evaluation2 = new Evaluation();

    public LOGIC_FUNCTION getCompositeFunction() {
        return compositeFunction;
    }

    public void setCompositeFunction(LOGIC_FUNCTION compositeFunction) {
        this.compositeFunction = compositeFunction;
    }

    public String getCompositeCondition() {
        return compositeCondition;
    }

    public void setCompositeCondition(String compositeCondition) {
        this.compositeCondition = compositeCondition;
    }

    public Evaluation getEvaluation1() {
        return evaluation1;
    }

    public void setEvaluation1(Evaluation evaluation1) {
        this.evaluation1 = evaluation1;
    }

    public Evaluation getEvaluation2() {
        return evaluation2;
    }

    public void setEvaluation2(Evaluation evaluation2) {
        this.evaluation2 = evaluation2;
    }

    public String generate() {

        StringBuilder sb = new StringBuilder();

        if (compositeFunction.getNumberOperand() == 1) {
            sb.append(compositeFunction.getFunction());
            sb.append("(");
            sb.append(evaluation1.generate());
            sb.append(")");
        } else if (compositeFunction.getNumberOperand() == 2) {
            sb.append("(");
            sb.append(evaluation1.generate());
            sb.append(")");
            sb.append(" ");
            sb.append(compositeFunction.getFunction());
            sb.append(" ");
            sb.append("(");
            sb.append(evaluation2.generate());
            sb.append(")");
        }

        return sb.toString();

    }

    public CompositeCondition getParent() {
        return parent;
    }

    public void setParent(CompositeCondition parent) {
        this.parent = parent;
    }

}
