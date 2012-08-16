package com.f1codz.jlisp.util;

import static org.apache.commons.lang.StringUtils.removeEnd;
import static org.apache.commons.lang.StringUtils.removeStart;
import static org.apache.commons.lang.StringUtils.trim;

public class LispExpressionUtils {

    private static final String QUOTE_STRING = "'";
    private static final char QUOTE_CHAR = '\'';
    private static final char LISP_START_CHAR = '(';
    private static final char LISP_END_CHAR = ')';

    public static String quote(String string) {
        return QUOTE_STRING + string + QUOTE_STRING;
    }

    public static String unquote(String string) {
        String trimmed = trim(string);
        return removeEnd(
                removeStart(
                        trimmed,
                        QUOTE_STRING
                ),
                QUOTE_STRING
        );
    }

    public static boolean isQuoted(String string) {
        String trimmed = trim(string);
        return trimmed.startsWith(QUOTE_STRING) && trimmed.endsWith(QUOTE_STRING);
    }

    public static boolean isNotAValidNormalUnitChar(char c) {
        return isSpaceChar(c) || c == LISP_START_CHAR || c == LISP_END_CHAR || c == QUOTE_CHAR;
    }

    public static boolean isSpaceChar(char c) {
        return org.apache.commons.lang.StringUtils.isWhitespace(String.valueOf(c));
    }
}