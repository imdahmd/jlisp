package com.f1codz.jlisp.type;

public class Literal extends LispType {
    public static final Literal INVALID = new Literal(null, null);
    public final Object value;

    public Literal(String symbol, Object value) {
        super(symbol);
        this.value = value;
    }

    public Literal(String symbol) {
        super(symbol);
        this.value = symbol;
    }

    public static boolean isValid(Literal literal) {
        return !INVALID.equals(literal);
    }
}