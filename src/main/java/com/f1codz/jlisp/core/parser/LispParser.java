package com.f1codz.jlisp.core.parser;

import com.f1codz.jlisp.exception.LispParseException;

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
     * Private Code
     */

    private Lisp lisp;

    private List<UnitParser> parsers;

    private LispParser(Lisp lisp) {
        parsers = new ArrayList<UnitParser>();
        parsers.add(new SublispParser());
        parsers.add(new QuotedUnitParser());
        parsers.add(new NormalUnitParser());

        this.lisp = lisp;
    }

    private boolean hasNextUnit() {
        return lisp.nextNonSpaceChar() != LISP_END;
    }

    private String nextUnit() {
        char currentChar = lisp.currentChar();
        for (UnitParser unitParser : parsers) {
            if (unitParser.fitsFor(currentChar)) {
                return unitParser.parse(lisp);
            }
        }

        throw new LispParseException(lisp.value());
    }
}