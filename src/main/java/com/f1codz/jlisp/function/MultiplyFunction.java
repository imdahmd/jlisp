package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

import static com.f1codz.jlisp.util.Assertions.assertNumber;
import static com.f1codz.jlisp.util.Assertions.assertString;
import static com.f1codz.jlisp.util.LispExpressionUtils.quote;

public class MultiplyFunction extends Function {
    public MultiplyFunction() {
        super("*");
    }

    @Override
    public LispType apply(List<LispType> params) {
        return multiply(params);
    }

    private LispType multiply(List<LispType> params) {
        Double result = (double) 1;

        for (LispType param : params) {
            Literal literal = assertLiteral(param);
            Number value = assertNumber(literal);
            result *= (Double) value;
        }

        return new Literal(result.toString(), result);
    }
}