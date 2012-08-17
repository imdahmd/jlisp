package com.f1codz.jlisp.core.parser;

import java.util.ArrayList;
import java.util.List;

import static com.f1codz.jlisp.util.LispExpressionUtils.*;

public class LispParser {
    public static boolean isLisp(final String expression) {
        String trimmed = trim(expression);
        return trimmed.startsWith(String.valueOf(LISP_START)) && trimmed.endsWith(String.valueOf(LISP_END));
    }

    public static List<String> unpack(final String lisp) {
        List<String> result = new ArrayList<String>();

        LispParser lispParser = new LispParser(new Lisp(lisp));
        while (lispParser.hasNextUnit()) {
            result.add(lispParser.nextUnit());
        }

        return result;
    }

    /**
     * *************************** Private Code ****************************
     */

    private Lisp lisp;

    private LispParser(Lisp lisp) {
        this.lisp = lisp;
    }

    private boolean hasNextUnit() {
        return lisp.nextNonSpaceChar() != LISP_END;
    }

    private String nextUnit() {
        return UnitParsers.nextFor(lisp).parse(lisp);
    }
}