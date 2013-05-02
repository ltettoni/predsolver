package org.logic2j.predsolver.api;

import java.util.Collection;
import java.util.Comparator;

/**
 * A {@link Var} is a typed and named placeholder used to represent the same
 * value(s) in a logical expression. It is used to specify parameters to a
 * problem, or to identify results from the solution.
 * 
 * @author Laurent
 * @param <T>
 */
public class Var<T> implements Term {

    private static final char PRIVATE_VAR_PREFIX = '_';
    private static int anonymousNamingCounter = 0;

    private String name = null; // Optional name
    public final static Comparator<? super Var<?>> COMPARATOR_BY_NAME = new Comparator<Var<?>>() {

        @Override
        public int compare(Var<?> v0, Var<?> v1) {
            return v0.getName().compareTo(v1.getName());
        }
    };

    // FIXME Will a Var need to carry its runtime type?

    // Anonymous variable
    public Var() {
        this(null);
    }

    // Public named var
    public Var(String theName) {
        if (theName == null) {
            this.name = String.valueOf(PRIVATE_VAR_PREFIX) + 'v' + (++anonymousNamingCounter);
        } else {
            if (theName.charAt(0)==PRIVATE_VAR_PREFIX) {
                throw new IllegalArgumentException("Cannot name a public variable as \"" + theName + "\" - cannot start with " + PRIVATE_VAR_PREFIX);
            }
            this.name = theName;
        }
    }

    /**
     * Public variables are those accessible across predicate invocations, and 
     * can be extracted from the solver.
     * @return
     */
    public boolean isPublic() {
        return this.name.charAt(0) != PRIVATE_VAR_PREFIX;
    }

    public String getName() {
        return name;
    }

    // -----------------
    // Convert to Bindings
    // -----------------

    public Binding<T> freeBinding() {
        return new Binding<T>(this);
    }

    public Binding<T> boundTo(Collection<T> theValues) {
        return new Binding<T>(this, theValues);
    }

    public Binding<T> boundTo(T... theValues) {
        return new Binding<T>(this, theValues);
    }

    // -----------------
    // Core Object
    // -----------------

    @Override
    public String toString() {
        if (name != null) {
            return name;
        }
        return "Var@" + Integer.toHexString(this.hashCode());
    }

}
