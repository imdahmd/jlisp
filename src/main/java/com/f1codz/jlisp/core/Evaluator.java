package com.f1codz.jlisp.core;

import com.f1codz.jlisp.exception.FunctionExpectedException;
import com.f1codz.jlisp.factory.LispTypeFactory;
import com.f1codz.jlisp.type.Function;
import com.f1codz.jlisp.type.LispType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.*;

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

            final LispType function = lispTypes.get(0);
            if (!(function instanceof Function))
                throw new FunctionExpectedException(function);

            final List<LispType> params = lispTypes.subList(1, lispTypes.size());
            return ((Function) function).apply(params);
        }

        return lispTypeFactory.from(expression);
    }

    private List<String> unpack(final String lisp) {
        return Arrays.asList(
                split(
                        removeEnd(
                                removeStart(
                                        lisp,
                                        "("),
                                ")"),
                        " ")
        );
    }

    private boolean isLisp(final String expression) {
        String trimmed = trim(expression);
        return trimmed.startsWith("(") && trimmed.endsWith(")");
    }
}
