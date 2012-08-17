package com.f1codz.jlisp.core.parser;

interface UnitParser {
    boolean fitsFor(char currentChar);
    String parse(Lisp lisp);
}
