package com.f1codz.jlisp.core.parser;

import static com.f1codz.jlisp.util.LispExpressionUtils.QUOTE_CHAR;

class QuotedUnitParser implements UnitParser {
        public boolean fitsFor(char currentChar) {
            return currentChar == QUOTE_CHAR;
        }

        public String parse(Lisp lisp) {
            char currentChar = lisp.currentChar();

            StringBuilder result = new StringBuilder();
            result.append(currentChar);

            while (true) {
                char nextChar = lisp.nextChar();
                result.append(nextChar);

                if (nextChar == QUOTE_CHAR)
                    break;
            }

            return result.toString();
        }
    }
