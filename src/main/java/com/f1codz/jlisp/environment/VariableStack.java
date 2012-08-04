package com.f1codz.jlisp.environment;

import com.f1codz.jlisp.type.LispType;

import java.util.Map;

public class VariableStack {
    Map<String, LispType> variables;

    public LispType get(String name) {
        LispType lispType = variables.get(name);
        if(lispType == null && isValidVariableName(name))
            return add(name, LispType.UNDEFINED);

        return lispType;
    }

    private LispType add(String name, LispType lispType) {
        variables.put(name, lispType);

        return lispType;
    }

    private boolean isValidVariableName(String name) {
        return true; //TODO
    }
}
