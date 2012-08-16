package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

import static com.f1codz.jlisp.util.Assertions.assertNumber;
import static com.f1codz.jlisp.util.Assertions.assertString;
import static com.f1codz.jlisp.util.LispExpressionUtils.quote;

public class PlusFunction extends Function {
    public PlusFunction() {
        super("+");
    }

    @Override
    public LispType apply(List<LispType> params) {
        Literal firstLiteral = assertLiteral(params.get(0));
        Object firstParamValue = firstLiteral.value;

        if (firstParamValue instanceof String)
            return stringConcatenation(params);
        else
            return addition(params);
    }

    private LispType stringConcatenation(List<LispType> params) {
        StringBuilder buffer = new StringBuilder();
        for (LispType param : params) {
            Literal literal = assertLiteral(param);
            String value = assertString(literal);
            buffer.append(value);
        }

        String result = buffer.toString();
        return new Literal(quote(result), result);
    }

    private LispType addition(List<LispType> params) {
        Double result = (double) 0;

        for (LispType param : params) {
            Literal literal = assertLiteral(param);
            Number value = assertNumber(literal);
            result += (Double) value;
        }

        return new Literal(result.toString(), result);
    }
}