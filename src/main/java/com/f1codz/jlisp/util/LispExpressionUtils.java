package com.f1codz.jlisp.util;

import static java.lang.Character.isWhitespace;

public class LispExpressionUtils {

    public static final char QUOTE_CHAR = '\'';
    public static final char LISP_START = '(';
    public static final char LISP_END = ')';

    public static String quote(String string) {
        return String.valueOf(QUOTE_CHAR) + string + String.valueOf(QUOTE_CHAR);
    }

    public static String unquote(String string) {
        String trimmed = trim(string);
        return removeEnd(
                removeStart(
                        trimmed,
                        String.valueOf(QUOTE_CHAR)
                ),
                String.valueOf(QUOTE_CHAR)
        );
    }

    public static boolean isQuoted(String string) {
        String trimmed = trim(string);
        return trimmed.startsWith(String.valueOf(QUOTE_CHAR)) && trimmed.endsWith(String.valueOf(QUOTE_CHAR));
    }

    public static boolean isNormalUnitChar(char c) {
        return !(isWhitespace(c) || c == LISP_START || c == LISP_END || c == QUOTE_CHAR);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public static String removeStart(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)){
            return str.substring(remove.length());
        }
        return str;
    }
}