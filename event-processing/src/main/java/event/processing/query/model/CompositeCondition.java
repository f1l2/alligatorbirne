package event.processing.query.model;

import event.processing.query.Query.LOGIC_FUNCTION;

public class CompositeCondition extends Condition {

    private LOGIC_FUNCTION compositeFunction;

    private String compositeCondition = null;

    private SingleCondition sc = null;

    private CompositeCondition cc = null;

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

    public SingleCondition getSc() {
        return sc;
    }

    public void setSc(SingleCondition sc) {
        this.sc = sc;
    }

    public CompositeCondition getCc() {
        return cc;
    }

    public void setCc(CompositeCondition sc) {
        this.cc = sc;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        if (null != cc) {
            if (compositeFunction.getNumberOperand() == 1) {
                sb.append(compositeFunction.getFunction());
                // sb.append("(");
                sb.append(cc.generate());
                // sb.append(")");
            } else if (compositeFunction.getNumberOperand() == 2) {
                // sb.append("(");
                sb.append(sc.generate());
                // sb.append(")");
                sb.append(" ");
                sb.append(compositeFunction.getFunction());
                sb.append(" ");
                // sb.append("(");
                sb.append(cc.generate());
                // sb.append(")");
            }
        } else {
            return sc.generate();
        }

        return sb.toString();
    }

    public String generateInclPrefix() {
        StringBuilder sb = new StringBuilder();
        if (null != cc) {
            if (compositeFunction.getNumberOperand() == 1) {
                sb.append(compositeFunction.getFunction());
                sb.append("(");
                sb.append(cc.generateInclPrefix());
                sb.append(")");
            } else if (compositeFunction.getNumberOperand() == 2) {
                // sb.append("(");
                sb.append(sc.generateInclPrefix());
                // sb.append(")");
                sb.append(" ");
                sb.append(compositeFunction.getFunction());
                sb.append(" ");
                // sb.append("(");
                sb.append(cc.generateInclPrefix());
                // sb.append(")");
            }
        } else {
            return sc.generateInclPrefix();
        }

        return sb.toString();
    }

}
