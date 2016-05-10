package org.logic2j.predsolver.predicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.LogicProvider;

public class Member<T> extends Predicate1<T> {

    private final List<T> orderedValues; // Returned in order as specified
    private final Set<T> bag; // Efficient "contains()"

    public Member(Term x, T... values) {
        this(LogicProvider.INSTANCE, "member", x, values);
    }

    public Member(Provider provider, String theName, Term x, T... values) {
        super(provider, theName, x);
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
