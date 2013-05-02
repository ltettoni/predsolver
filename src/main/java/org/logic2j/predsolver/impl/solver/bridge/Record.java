package org.logic2j.predsolver.impl.solver.bridge;

import java.util.IdentityHashMap;
import java.util.Set;
import java.util.TreeMap;

import org.logic2j.predsolver.api.Var;

public class Record {

    protected final IdentityHashMap<Var<?>, Object> data = new IdentityHashMap<Var<?>, Object>(5);

    public Record() {
    }

    public <T> Record(Var<T> var, Object value) {
        this.data.put(var, value);
    }

    /**
     * Copy constructor
     * 
     * @param source
     */
    public Record(Record source) {
        this.data.putAll(source.data);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Var<T> var) {
        return (T) this.data.get(var);
    }

    public <T> Record set(Var<T> theVar, Object theValue) {
        this.data.put(theVar, theValue);
        return this; // Allow chaining
    }

    public Record setAll(Record toAdd) {
        this.data.putAll(toAdd.data);
        return this;
    }

    public Set<Var<?>> vars() {
        return this.data.keySet();
    }

    @Override
    public String toString() {
        final TreeMap<Var<?>, Object> displayable = new TreeMap<Var<?>, Object>(Var.COMPARATOR_BY_NAME);
        displayable.putAll(this.data);
        return "Record" + displayable;
    }

}
