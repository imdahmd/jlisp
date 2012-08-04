package com.f1codz.jlisp.exception;

import com.f1codz.jlisp.type.LispType;

public class FunctionExpectedException extends IllegalArgumentException {
    public FunctionExpectedException(LispType function) {
        super(String.format("Expected function, instead found %s", function));
    }
}
