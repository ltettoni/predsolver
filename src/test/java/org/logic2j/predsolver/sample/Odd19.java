package org.logic2j.predsolver.sample;

import java.util.Arrays;
import java.util.List;

import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.predicate.Predicate1;

public class Odd19 extends Predicate1<Integer> {

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
