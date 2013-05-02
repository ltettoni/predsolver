package org.logic2j.predsolver.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.api.tuple.Tuple;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.pred.And;
import org.logic2j.predsolver.solve.bridge.Bridge;
import org.logic2j.predsolver.solve.bridge.Record;

/**
 * A {@link Predicate} relate {@link Term}(s) together to express true
 * statements.
 * 
 * @author Laurent
 */
public class Predicate implements Term {

    @SuppressWarnings("unused")
    private Provider provider;
    private final String name;
    public Term terms[];

    public Predicate(Provider theProvider, String theName, Term... theArguments) {
        for (Term term : theArguments) {
            if (term instanceof Binding<?>) {
                if (((Binding<?>) term).getVar().isPublic()) {
                    throw new IllegalArgumentException("Cannot instantiate predicate " + theName + " with argument " + term
                            + " because it is bound and not anonymous");
                }
            }
        }
        this.provider = theProvider;
        this.name = theName;
        this.terms = theArguments;
    }

    public void apply(Bridge bridge) {
        throw new UnsupportedOperationException("Not implemented in superclass: " + this + ".apply(" + bridge + ')');
    }

    // -----------------
    // Logical operators (placed here for a fluent API)
    // -----------------

    public Predicate and(Predicate that) {
        return new And(this, that);
    }

    public Predicate or(Predicate that) {
        return new Predicate(LogicProvider.INSTANCE, "or", this, that);
    }

    public Predicate not(Predicate that) {
        return new Predicate(LogicProvider.INSTANCE, "not", that);
    }

    public Predicate negated() {
        return new Predicate(LogicProvider.INSTANCE, "not", this);
    }

    // -----------------
    // Accessors
    // -----------------

    public String getName() {
        return name;
    }

    public Term[] getTerms() {
        return terms;
    }

    public int arity() {
        return terms.length;
    }

    // -----------------
    // Support methods for predicates real implementation
    // -----------------

    @SuppressWarnings("unchecked")
    public <T> Var<T> var(int i) {
        if (terms[i] instanceof Binding<?>) {
            return ((Binding<T>) terms[i]).getVar();
        } else if (terms[i] instanceof Var<?>) {
            return (Var<T>) terms[i];
        } else {
            return null; // on and(pred, pred, ...) if we call var(i) we can't
                         // throw an exception
            // throw new IllegalArgumentException("variable " + terms[i] +
            // " is neither a Var nor a Binding");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Binding<T> binding(int i) {
        if (terms[i] instanceof Binding<?>) {
            return (Binding<T>) terms[i];
        } else if (terms[i] instanceof Var<?>) {
            return ((Var<T>) terms[i]).freeBinding();
        }
        throw new IllegalArgumentException("variable " + terms[i] + " is neither a Var nor a Binding");
    }

    public Set<Var<?>> publicVars() {
        final int arity = this.terms.length;
        final Set<Var<?>> result = new HashSet<Var<?>>();
        for (int i = 0; i < arity; i++) {
            final Var<Object> var = var(i);
            if (var.isPublic()) {
                result.add(var);
            }
        }
        return result;
    }

    protected boolean isBound(Bridge bridge, int termOrdinal) {
        final Binding<Object> binding = binding(termOrdinal);
        if (binding.isBound()) {
            return true;
        }
        // if (terms[termOrdinal] instanceof Binding<?> && ((Binding<?>)
        // terms[termOrdinal]).isBound()) {
        // return true;
        // }
        final Var<Object> var = var(termOrdinal);
        boolean bound = bridge.isBound(var);
        return bound;
    }

    protected List<Record> fetch(Bridge bridge, int... termOrdinals) {
        for (int termOrdinal : termOrdinals) {
            if (terms[termOrdinal] instanceof Binding<?>) {
                Binding<?> binding = (Binding<?>) terms[termOrdinal];
                bridge.cartesianColumn(binding);
            }
        }
        List<Record> records = bridge.fetch();
        return records;
    }

    protected List<Tuple> single(Tuple value) {
        final List<Tuple> result = new ArrayList<Tuple>();
        result.add(value);
        return result;
    }

    // -----------------
    // Core Object
    // -----------------

    @Override
    public String toString() {
        if (this.terms == null) {
            return this.name;
        }
        String asList = String.valueOf(Arrays.asList(this.terms));
        return this.name + '(' + asList.substring(1, asList.length() - 1) + ')';
    }

}
