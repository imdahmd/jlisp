package com.f1codz.jlisp.core;

import com.f1codz.jlisp.factory.LispTypeFactory;
import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;

import java.util.ArrayList;
import java.util.List;

import static com.f1codz.jlisp.core.parser.LispParser.isLisp;
import static com.f1codz.jlisp.core.parser.LispParser.unpack;
import static com.f1codz.jlisp.util.Assertions.assertFunction;

public class Evaluator {
    private LispTypeFactory lispTypeFactory;

    public Evaluator(LispTypeFactory lispTypeFactory) {
        this.lispTypeFactory = lispTypeFactory;
    }

    public LispType evaluate(String expression) {
        if (isLisp(expression)) {
            final List<LispType> lispTypes = new ArrayList<LispType>();

            for (String subLisp : unpack(expression)) {
                lispTypes.add(evaluate(subLisp));
            }

            final Function function = assertFunction(lispTypes.get(0));

            final List<LispType> params = lispTypes.subList(1, lispTypes.size());
            return function.apply(params);
        }

        return lispTypeFactory.typeFrom(expression);
    }
}