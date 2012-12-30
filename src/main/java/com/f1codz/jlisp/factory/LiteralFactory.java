package com.f1codz.jlisp.factory;

import com.f1codz.jlisp.type.Fraction;
import com.f1codz.jlisp.type.Literal;

import static com.f1codz.jlisp.util.LispExpressionUtils.isQuoted;
import static com.f1codz.jlisp.util.LispExpressionUtils.unquote;

public class LiteralFactory {
    public static Literal from(String symbol) {
        try {
            Double value = Double.parseDouble(symbol);
            return new Literal(symbol, value);
        } catch (NumberFormatException nfe) {
        }

        try {
            Integer value = Integer.parseInt(symbol);
            return new Literal(symbol, value);
        } catch (NumberFormatException nfe) {
        }

        if ("true".equals(symbol) || "false".equals(symbol)) {
            Boolean value = Boolean.parseBoolean(symbol);
            return new Literal(symbol, value);
        }

        if (isQuoted(symbol)) {
            return new Literal(symbol, unquote(symbol));
        }

        try {
            return Fraction.parseFraction(symbol);
        } catch (NumberFormatException nfe) {
        }

        return Literal.INVALID;
    }
}