package com.f1codz.jlisp.environment;

import com.f1codz.jlisp.core.Evaluator;
import com.f1codz.jlisp.factory.LispTypeFactory;
import com.f1codz.jlisp.function.PlusFunction;

public class LispEnv {
    private Evaluator evaluator;

    public LispEnv() {
        VariableStack variableStack = new VariableStack();
        variableStack.add(new PlusFunction());

        LispTypeFactory lispTypeFactory = new LispTypeFactory(variableStack);

        this.evaluator = new Evaluator(lispTypeFactory);
    }

    public String eval(String lispExpression) {
        return evaluator.evaluate(lispExpression).symbol;
    }
}