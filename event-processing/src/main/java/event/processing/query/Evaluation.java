package event.processing.query;

import event.processing.query.Query.OPERATOR;

public class Evaluation {

    private OPERATOR operator;

    private String evaluation;

    private String property1 = null;

    private String property2 = null;

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public OPERATOR getOperator() {
        return operator;
    }

    public void setOperator(OPERATOR operator) {
        this.operator = operator;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(property1);
        sb.append(" ");
        sb.append(operator.getSign());
        sb.append(" ");
        sb.append(property2);

        return sb.toString();
    }
}
