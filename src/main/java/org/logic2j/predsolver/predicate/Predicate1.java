package org.logic2j.predsolver.predicate;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.impl.solver.bridge.Record;

public abstract class Predicate1<T> extends Predicate {

    public Predicate1(Provider provider, String theName, Term x) {
        super(provider, theName, x);
    }

    protected abstract boolean membershipFunction(T value);
    
    protected abstract Iterable<T> generatorFunction();
    
    @Override
    public final void apply(Bridge bridge) {
        final Binding<T> binding0 = binding(0);
        if (binding0.isBound()) {
            for (T v : binding0.getValues()) {
                if (membershipFunction(v)) {
                    bridge.add(new Record());
                }
            }
        } else {
            final Var<T> var0 = binding0.getVar();
            if (bridge.isBound(var0)) {
                for (Record rec : bridge.fetch(var0)) {
                    final T v = bridge.get(rec, var0);
                    if (! membershipFunction(v)) {
                        bridge.remove(rec);
                    }
                }
            } else {
                // Free var
                final Iterable<T> generator = generatorFunction();
                // Should we check if generator == null meaning the function cannot generate???
                for (T i: generator) {
                    bridge.add(new Record(var0, i));
                }
            }
        }
    }

}
