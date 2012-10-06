package com.f1codz.jlisp.core.parser;

import static com.f1codz.jlisp.util.LispExpressionUtils.*;
import static java.lang.Character.isWhitespace;

class NormalUnitParser implements UnitParser {
    public boolean fitsFor(char currentChar) {
        return isNormalUnitChar(currentChar);
    }

    public String parse(Lisp lisp) {
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

    private boolean isNormalUnitChar(char c) {
        return !(isWhitespace(c) || c == LISP_START || c == LISP_END || c == QUOTE_CHAR);
    }
}
