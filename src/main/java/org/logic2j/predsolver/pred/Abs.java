package org.logic2j.predsolver.pred;

import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.model.Term;

public class Abs extends Predicate2<Integer, Integer> {

    public Abs(Term x, Term y) {
        super(LogicProvider.INSTANCE, "abs", x, y);
    }

    @Override
    public Integer[] forward(Integer x) {
        return new Integer[] { Math.abs(x) };
    }

    @Override
    public Integer[] reverse(Integer image) {
        if (image > 0) {
            return new Integer[] { image, -image };
        } else if (image == 0) {
            return new Integer[] { 0 };
        } else {
            return new Integer[0];
        }
    }

    @Override
    public boolean filter(Integer x, Integer y) {
        return y == Math.abs(x);
    }

}
