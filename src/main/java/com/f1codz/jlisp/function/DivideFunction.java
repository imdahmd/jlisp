package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Fraction;
import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

import static com.f1codz.jlisp.util.Assertions.assertNumber;

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
            Literal literal = assertLiteral(param);
            Fraction value = assertFraction(literal);
            result.divide(value);
        }

        return new Literal(result.toString(), result);
    }

    private Fraction assertFraction(Literal literal) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}