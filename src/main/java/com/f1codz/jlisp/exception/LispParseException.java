package com.f1codz.jlisp.exception;

public class LispParseException extends IllegalStateException {
    public LispParseException(String lisp) {
        super(String.format("Malformed lisp %s", lisp));
    }
}
