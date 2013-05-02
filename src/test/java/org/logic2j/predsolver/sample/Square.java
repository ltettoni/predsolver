package org.logic2j.predsolver.sample;

import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.pred.Predicate2;

public class Square extends Predicate2<Integer, Integer> {

    public Square(Provider theProvider, String theName, Term x, Term y) {
        super(theProvider, theName, x, y);
    }

    public Square(Term x, Term y) {
        super(LogicProvider.INSTANCE, "square", x, y);
    }

    @Override
    public Integer[] direct(Integer x) {
        return new Integer[] { x * x };
    }

    @Override
    public Integer[] inverse(Integer image) {
        int sqrt = (int) Math.sqrt(image);
        if (sqrt * sqrt == image) {
            if (image > 0) {
                return new Integer[] { sqrt, -sqrt };
            } else {
                return new Integer[] { sqrt };
            }
        } else {
            return new Integer[0];
        }
    }

    @Override
    public boolean filter(Integer x, Integer y) {
        return x * x == y;
    }

}
