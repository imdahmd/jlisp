package com.f1codz.jlisp.function;

import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

import java.util.List;

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

    private String quote(String string) {
        return "'" + string + "'";
    }

    private String assertString(Literal literal) {
        if (!(literal.value instanceof String))
            throw new UnsupportedOperationException(String.format("Expected string, found %s", literal.symbol));

        return (String) literal.value;
    }

    private Number assertNumber(Literal literal) {
        if (!(literal.value instanceof Number))
            throw new UnsupportedOperationException(String.format("Expected number, found %s", literal.symbol));

        return (Number) literal.value;
    }
}
