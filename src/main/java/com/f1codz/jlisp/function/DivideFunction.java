package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Fraction;
import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

public class DivideFunction extends Function {
    public DivideFunction() {
        super("/");
    }

    @Override
    public LispType apply(List<LispType> params) {
        return divide(params);
    }

    private LispType divide(List<LispType> params) {
        Fraction result = assertFraction(assertLiteral(params.get(0)));

        for (LispType param : params.subList(1, params.size())) {
            result = result.divide(assertFraction(assertLiteral(param)));
        }

        return result;
    }

    private Fraction assertFraction(Literal literal) {
        if(literal instanceof Fraction){
            return (Fraction) literal;
        } else {
            return Fraction.parseFraction(literal.symbol);
        }
    }
}