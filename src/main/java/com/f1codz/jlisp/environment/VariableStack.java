package com.f1codz.jlisp.environment;

import com.f1codz.jlisp.type.LispType;

import java.util.HashMap;
import java.util.Map;

public class VariableStack {
    Map<String, LispType> variables;

    public VariableStack() {
        variables = new HashMap<String, LispType>();
    }

    public LispType get(String name) {
        LispType lispType = variables.get(name);
        if (lispType == null && isValidVariableName(name))
            return add(LispType.UNDEFINED);

        return lispType;
    }

    public LispType add(LispType lispType) {
        variables.put(lispType.symbol, lispType);

        return lispType;
    }

    private boolean isValidVariableName(String name) {
        return true; //TODO
    }
}