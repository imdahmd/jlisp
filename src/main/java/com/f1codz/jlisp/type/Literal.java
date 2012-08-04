package com.f1codz.jlisp.type;

public class Literal extends LispType {
    public static final Literal INVALID = new Literal(null, null);
    private String symbol;
    private Object value;

    public Literal(String symbol, Object value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static boolean isValid(Literal literal) {
        return !INVALID.equals(literal);
    }
}
