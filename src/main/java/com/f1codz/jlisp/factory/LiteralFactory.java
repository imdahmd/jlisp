package com.f1codz.jlisp.factory;

import com.f1codz.jlisp.type.Literal;

import static org.apache.commons.lang.StringUtils.trim;

public class LiteralFactory {
    public static Literal from(String symbol) {
        try{
            Double value = Double.parseDouble(symbol);
            return new Literal(symbol, value);
        }catch (NumberFormatException nfe) {}

        try{
            Integer value = Integer.parseInt(symbol);
            return new Literal(symbol, value);
        }catch (NumberFormatException nfe) {}

        try{
            Boolean value = Boolean.parseBoolean(symbol);
            return new Literal(symbol, value);
        }catch (NumberFormatException nfe) {}

        if(isQuoted(symbol)) {
            return new Literal(symbol, symbol);
        }

        return Literal.INVALID;
    }

    private static boolean isQuoted(String symbol) {
        String trimmed = trim(symbol);
        return trimmed.startsWith("'") && trimmed.endsWith("'");
    }
}
