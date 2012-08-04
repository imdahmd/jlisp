package com.f1codz.jlisp.exception;

public class UnidentifiedSymbolException extends IllegalArgumentException {
    public UnidentifiedSymbolException(String symbol) {
        super(String.format("Unidentified symbol %s", symbol));
    }
}
