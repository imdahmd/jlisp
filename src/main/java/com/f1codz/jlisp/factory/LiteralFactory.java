package com.f1codz.jlisp.factory;

import com.f1codz.jlisp.type.Literal;

import static org.apache.commons.lang.StringUtils.removeEnd;
import static org.apache.commons.lang.StringUtils.removeStart;
import static org.apache.commons.lang.StringUtils.trim;

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

        return Literal.INVALID;
    }

    private static String unquote(String string) {
        String trimmed = trim(string);
        return removeEnd(
                removeStart(
                        trimmed,
                        "'"
                ),
                "'"
        );
    }

    private static boolean isQuoted(String string) {
        String trimmed = trim(string);
        return trimmed.startsWith("'") && trimmed.endsWith("'");
    }
}
