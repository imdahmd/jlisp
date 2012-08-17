package com.f1codz.jlisp.core.parser;

import com.f1codz.jlisp.exception.LispParseException;

import static com.f1codz.jlisp.util.LispExpressionUtils.trim;
import static java.lang.Character.isWhitespace;

final class Lisp {
    private String value;
    private int curPos;

    Lisp(String value) {
        this.value = trim(value);
        this.curPos = 0;
    }

    char nextNonSpaceChar() {
        do {
            curPos++;
        } while (isWhitespace(currentChar()));

        return currentChar();
    }

    char nextChar() {
        curPos++;
        return currentChar();
    }

    char prevChar() {
        curPos--;
        return currentChar();
    }

    char currentChar() {
        try {
            return value.charAt(curPos);
        } catch (StringIndexOutOfBoundsException e) {
            throw new LispParseException(value);
        }
    }

    String value() {
        return value;
    }
}
