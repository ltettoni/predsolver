package org.logic2j.predsolver.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A {@link Binding} is the endpoint of a constraint, it can hold either zero,
 * one, or several simultaneous values, finite or infinite.
 * 
 * It is used to specify parameters to a problem, or to extract results from the
 * solution.
 * 
 * @author Laurent
 * @param <T>
 */
public class Binding<T> implements Term {

    private final Var<T> var;
    private List<T> values = null;

    public Binding(Var<T> var) {
        this.var = var;
    }

    public Binding(Var<T> var, Collection<T> theValues) {
        this(var);
        setValues(theValues);
    }

    public Binding(Var<T> var, T... theValues) {
        this(var);
        setValues(theValues);
    }

    /**
     * Factory for a constant value.
     * 
     * @param constants
     * @return A {@link Var} whose value is bound (and fixed).
     */
    public static <T> Binding<T> cst(T... theValues) {
        final String varName;
        if (theValues.length == 1) {
            varName = "_cst(" + theValues[0] + ')';
        } else {
            varName = null;
        }
        final Var<T> var = new Var<T>(varName);
        final Binding<T> binding = new Binding<T>(var);
        binding.setValues(theValues);
        return binding;
    }

    // -----------------
    // Accessors
    // -----------------

    public Var<T> getVar() {
        return var;
    }

    // Set values

    public void setValue(T theValue) {
        this.values = new ArrayList<T>(1);
        this.values.add(theValue);
    }

    public void setValues(Collection<T> theValues) {
        this.values = new ArrayList<T>(theValues);
    }

    public void setValues(T... theValues) {
        this.values = Arrays.asList(theValues);
    }

    // Get single or multiple

    public T getValue() {
        if (!isBound()) {
            throw new IllegalArgumentException("Term " + this + " is not bound");
        }
        if (!isScalar()) {
            throw new IllegalArgumentException("Term " + this + " is not a scalar");
        }
        return this.values.get(0);
    }

    public List<T> getValues() {
        if (!isBound()) {
            throw new IllegalArgumentException("Term " + this + " is not bound");
        }
        return this.values;
    }

    // -----------------
    // Other
    // -----------------

    public Integer size() {
        if (this.values == null) {
            // The size of an unbound binding is null, not zero
            return null;
        } else {
            return new Integer(this.values.size());
        }
    }

    public boolean isScalar() {
        if (this.values == null) {
            return false;
        }
        return Long.valueOf(1).equals(size());
    }

    public boolean isBound() {
        return this.values != null;
    }

    public boolean isFree() {
        return !isBound();
    }

    public Binding<T> bind(List<T> theValues) {
        Binding<T> result = new Binding<T>(this.var);
        result.setValues(theValues);
        return result;
    }

    // -----------------
    // Core Object
    // -----------------

    @Override
    public String toString() {
        return String.valueOf(this.var) + '=' + this.values;
    }

}
