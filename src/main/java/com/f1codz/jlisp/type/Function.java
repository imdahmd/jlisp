package com.f1codz.jlisp.type;

import java.util.List;

public abstract class Function extends LispType {
    public Function(String symbol) {
        super(symbol);
    }

    public abstract LispType apply(List<LispType> params);

    protected Literal assertLiteral(LispType param) {
        if(!(param instanceof Literal))
            throw new UnsupportedOperationException(String.format("function %s cant be applied to %s", this.symbol, param.symbol));

        return (Literal) param;
    }
}
