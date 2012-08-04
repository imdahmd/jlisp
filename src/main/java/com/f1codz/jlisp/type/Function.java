package com.f1codz.jlisp.type;

import java.util.List;

public abstract class Function extends LispType {
    public abstract LispType apply(List<LispType> params);
}
