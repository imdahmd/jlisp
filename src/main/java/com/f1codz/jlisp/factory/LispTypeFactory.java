package com.f1codz.jlisp.factory;

import com.f1codz.jlisp.environment.VariableStack;
import com.f1codz.jlisp.exception.UnidentifiedSymbolException;
import com.f1codz.jlisp.type.LispType;
import com.f1codz.jlisp.type.Literal;

public class LispTypeFactory {
    private VariableStack variableStack;

    public LispTypeFactory(VariableStack variableStack) {
        this.variableStack = variableStack;
    }

    public LispType from(String symbol) {
        Literal literal = LiteralFactory.from(symbol);
        if (Literal.isValid(literal))
            return literal;

        LispType lispType = variableStack.get(symbol);
        if (lispType != null)
            return lispType;

        throw new UnidentifiedSymbolException(symbol);
    }
}
