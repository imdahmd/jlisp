package com.f1codz.jlisp.type;

public class LispType {
    public static final LispType UNDEFINED = new LispType("undefined");
    public final String symbol;

    public LispType(String symbol) {
        this.symbol = symbol;
    }
}