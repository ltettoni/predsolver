package org.logic2j.predsolver.pred;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.model.Term;

public class Member<T> extends ScalarPredicate<T> {

    private final List<T> orderedValues; // Returned in order as specified
    private final Set<T> bag; // Efficient "contains()"

    public Member(Term x, T... values) {
        this("member", x, values);
    }

    public Member(String theName, Term x, T... values) {
        super(theName, x);
        this.orderedValues = Arrays.asList(values);
        this.bag = new HashSet<T>(Arrays.asList(values));
    }

    @Override
    protected boolean membershipFunction(T value) {
        return this.bag.contains(value);
    }

    @Override
    protected Iterable<T> generatorFunction() {
        return this.orderedValues;
    }

}
