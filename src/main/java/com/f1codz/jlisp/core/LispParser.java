package com.f1codz.jlisp.core;

import com.f1codz.jlisp.exception.LispParseException;

import java.util.ArrayList;
import java.util.List;

import static com.f1codz.jlisp.util.LispExpressionUtils.isNotAValidNormalUnitChar;
import static com.f1codz.jlisp.util.LispExpressionUtils.isSpaceChar;
import static org.apache.commons.lang.StringUtils.trim;

public class LispParser {
    private static final String LISP_START_STRING = "(";
    private static final String LISP_END_STRING = ")";

    private String lisp;
    private int curPos;

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
        return trimmed.startsWith(LISP_START_STRING) && trimmed.endsWith(LISP_END_STRING);
    }

    private static LispParser forLispExpression(String lisp) {
        return new LispParser(lisp);
    }

    private LispParser(String lisp) {
        this.lisp = trim(lisp);
        this.curPos = 0;
    }

    private boolean hasNextUnit() {
        return curPos != lisp.length() && nextNonSpaceChar() != ')';
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

        throw new LispParseException(lisp);
    }

    private String isNextUnit_SubLisp() {
        final char currentChar = currentChar();
        if (currentChar != '(')
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        int countSubSubLisps = 0;
        while (true) {
            char nextChar = nextChar();
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
        final char currentChar = currentChar();
        if (currentChar != '\'')
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        while (true) {
            char nextChar = nextChar();
            result.append(nextChar);

            if (nextChar == '\'')
                break;
        }

        return result.toString();
    }

    private String isNextUnit_Normal() {
        final char currentChar = currentChar();
        if (isNotAValidNormalUnitChar(currentChar))
            return null;

        StringBuilder result = new StringBuilder();
        result.append(currentChar);

        while (true) {
            char nextChar = nextChar();
            if (isNotAValidNormalUnitChar(nextChar)) {
                prevChar();
                break;
            }

            result.append(nextChar);
        }

        return result.toString();
    }

    private char nextNonSpaceChar() {
        do {
            curPos++;
        } while (isSpaceChar(currentChar()));

        return currentChar();
    }

    private char nextChar() {
        curPos++;

        return currentChar();
    }

    private char prevChar() {
        curPos--;

        return currentChar();
    }

    private char currentChar() {
        try {
            return lisp.charAt(curPos);
        } catch (StringIndexOutOfBoundsException e) {
            throw new LispParseException(lisp);
        }
    }
}