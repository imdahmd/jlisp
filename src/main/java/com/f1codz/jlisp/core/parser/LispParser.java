package com.f1codz.jlisp.core.parser;

import com.f1codz.jlisp.exception.LispParseException;

import java.util.ArrayList;
import java.util.List;

import static com.f1codz.jlisp.util.LispExpressionUtils.*;

public class LispParser {
    private Lisp lisp;

    public static List<String> undress(final String lisp) {
        List<String> result = new ArrayList<String>();

        LispParser lispParser = forLispExpression(lisp);
        while (lispParser.hasNextUnit()) {
            result.add(lispParser.nextUnit());
        }

        return result;
    }

    public static boolean isLisp(final String expression) {
        String trimmed = trim(expression);
        return trimmed.startsWith(String.valueOf(LISP_START)) && trimmed.endsWith(String.valueOf(LISP_END));
    }

    private LispParser(Lisp lisp) {
        this.lisp = lisp;
    }

    private static LispParser forLispExpression(String lisp) {
        return new LispParser(new Lisp(lisp));
    }

    private boolean hasNextUnit() {
        return lisp.nextNonSpaceChar() != LISP_END;
    }

    private String nextUnit() {
        String nextSubLispUnit = isNextUnit_SubLisp();
        if (nextSubLispUnit != null)
            return nextSubLispUnit;

        String nextQuotedUnit = isNextUnit_Quoted();
        if (nextQuotedUnit != null)
            return nextQuotedUnit;

        String nextNormalUnit = isNextUnit_Normal();
        if (nextNormalUnit != null)
            return nextNormalUnit;

        throw new LispParseException(lisp.value());
    }

    private String isNextUnit_SubLisp() {
        final char currentChar = lisp.currentChar();
        if (currentChar != '(')
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        int countSubSubLisps = 0;
        while (true) {
            char nextChar = lisp.nextChar();
            result.append(nextChar);

            if (nextChar == ')' && countSubSubLisps == 0)
                break;

            if (nextChar == '(')
                countSubSubLisps++;
            else if (nextChar == ')')
                countSubSubLisps--;
        }

        return result.toString();
    }

    private String isNextUnit_Quoted() {
        final char currentChar = lisp.currentChar();
        if (currentChar != '\'')
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        while (true) {
            char nextChar = lisp.nextChar();
            result.append(nextChar);

            if (nextChar == '\'')
                break;
        }

        return result.toString();
    }

    private String isNextUnit_Normal() {
        final char currentChar = lisp.currentChar();
        if (isNotAValidNormalUnitChar(currentChar))
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        while (true) {
            char nextChar = lisp.nextChar();
            if (isNotAValidNormalUnitChar(nextChar)) {
                lisp.prevChar();
                break;
            }

            result.append(nextChar);
        }

        return result.toString();
    }
}