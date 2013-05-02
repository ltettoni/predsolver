package org.logic2j.predsolver.api;

import org.logic2j.predsolver.impl.solver.bridge.Bridge;

public class NonImplementedPredicate extends Predicate {

    public NonImplementedPredicate(Provider provider, String name, Term... terms) {
        super(provider, name, terms);
    }

    @Override
    public void apply(Bridge bridge) {
        throw new UnsupportedOperationException("Not implemented: " + this + ".apply(" + bridge + ')');
    }
}
