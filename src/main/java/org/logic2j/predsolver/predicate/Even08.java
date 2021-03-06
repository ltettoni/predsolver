package org.logic2j.predsolver.predicate;

import java.util.Arrays;
import java.util.List;

import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.predicate.Predicate1;

public class Even08 extends Predicate1<Integer> {

    private static final List<Integer> LIST = Arrays.asList(new Integer[] { 0, 2, 4, 6, 8 });

    public Even08(Term x) {
        super(LogicProvider.INSTANCE, "even08", x);
    }

    @Override
    protected boolean membershipFunction(Integer value) {
        final int v = value;
        return v >= 0 && v <= 8 && v % 2 == 0;
    }

    @Override
    protected Iterable<Integer> generatorFunction() {
        return LIST;
    }

}
