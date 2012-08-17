package com.f1codz.jlisp.core.parser;

import com.f1codz.jlisp.exception.LispParseException;

import static com.f1codz.jlisp.util.LispExpressionUtils.*;

enum UnitParsers {
    SublispParser {
        @Override
        boolean fitsFor(char currentChar) {
            return currentChar == LISP_START;
        }

        @Override
        String parse(Lisp lisp) {
            char currentChar = lisp.currentChar();

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
    },
    QuotedUnitParser {
        @Override
        boolean fitsFor(char currentChar) {
            return currentChar == QUOTE_CHAR;
        }

        @Override
        String parse(Lisp lisp) {
            char currentChar = lisp.currentChar();

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
    },
    NormalUnitParser {
        @Override
        boolean fitsFor(char currentChar) {
            return isNormalUnitChar(currentChar);
        }

        @Override
        String parse(Lisp lisp) {
            char currentChar = lisp.currentChar();

            StringBuilder result = new StringBuilder();
            result.append(currentChar);

            while (true) {
                char nextChar = lisp.nextChar();
                if (!isNormalUnitChar(nextChar)) {
                    lisp.prevChar();
                    break;
                }

                result.append(nextChar);
            }

            return result.toString();
        }
    };

    static UnitParsers nextFor(Lisp lisp) {
        char currentChar = lisp.currentChar();
        for (UnitParsers unitParser : UnitParsers.values()) {
            if (unitParser.fitsFor(currentChar)) {
                return unitParser;
            }
        }

        throw new LispParseException(lisp.value());
    }

    abstract boolean fitsFor(char currentChar);

    abstract String parse(Lisp lisp);
}
