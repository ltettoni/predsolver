package org.logic2j.predsolver.pred;

import java.util.Arrays;
import java.util.List;

import org.logic2j.predsolver.model.Term;

public class Odd19 extends ScalarPredicate<Integer> {

    private static final List<Integer> LIST = Arrays.asList(new Integer[] { 1, 3, 5, 7, 9 });

    public Odd19(Term x) {
        super("odd19", x);
    }

    @Override
    protected boolean membershipFunction(Integer value) {
        final int v = value;
        return v >= 1 && v <= 9 && v % 2 == 1;
    }

    @Override
    protected Iterable<Integer> generatorFunction() {
        return LIST;
    }

}
