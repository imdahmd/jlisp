package com.f1codz.jlisp.util;

import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

public class Assertions {
    public static Function assertFunction(LispType lispType) {
        if (!(lispType instanceof Function))
            throw new UnsupportedOperationException(String.format("Expected function, found %s", lispType.symbol));
        
        return (Function) lispType;
    }

    public static String assertString(Literal literal) {
        if (!(literal.value instanceof String))
            throw new UnsupportedOperationException(String.format("Expected string, found %s", literal.symbol));

        return (String) literal.value;
    }

    public static Number assertNumber(Literal literal) {
        if (!(literal.value instanceof Number))
            throw new UnsupportedOperationException(String.format("Expected number, found %s", literal.symbol));

        return (Number) literal.value;
    }
}