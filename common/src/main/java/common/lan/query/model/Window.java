package common.lan.query.model;

import common.lang.query.QueryLang.KEYWORD;

public class Window {

    private KEYWORD type;

    private String value;

    public KEYWORD getType() {
        return type;
    }

    public void setType(KEYWORD type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(".");
        sb.append(type.getKeyword());
        sb.append("(");
        sb.append(value);
        sb.append(")");
        return sb.toString();
    }
}
