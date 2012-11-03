package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

import static com.f1codz.jlisp.util.Assertions.assertNumber;

public class SubtractFunction extends Function {
    public SubtractFunction() {
        super("-");
    }

    @Override
    public LispType apply(List<LispType> params) {
        return subtract(params);
    }

    private LispType subtract(List<LispType> params) {
        Double result = (Double) assertNumber(assertLiteral(params.get(0)));

        for (LispType param : params.subList(1, params.size())) {
            Literal literal = assertLiteral(param);
            Number value = assertNumber(literal);
            result -= (Double) value;
        }

        return new Literal(result.toString(), result);
    }
}