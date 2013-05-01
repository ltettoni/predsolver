package org.logic2j.predsolver.solve.bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.model.Binding;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.tuple.Tuple1;
import org.logic2j.predsolver.tuple.Tuple2;
import org.logic2j.predsolver.tuple.Tuple3;

public class BridgeImpl implements Bridge {

    // private static final Set<Var<?>> EMPTY_SET = Collections.emptySet();
    private static final List<?> EMPTY_LIST = Collections.emptyList();

    private List<Record> records;
    private Set<Var<?>> vars;

    /**
     * Empty constructor.
     */
    public BridgeImpl() {
        this.records = null;
        this.vars = new HashSet<Var<?>>();
    }

    /**
     * Copy constructor
     * 
     * @param source
     */
    public BridgeImpl(Bridge source) {
        this.records = new LinkedList<Record>();
        this.vars = new HashSet<Var<?>>(source.vars());
        // Deep cloning
        for (Record rec : source.fetch()) {
            this.records.add(new Record(rec));
        }
    }

    @Override
    public void parallelColumn(Binding<?> extraBinding) {
        if (extraBinding.isBound()) {
            this.vars.add(extraBinding.var);
            List<?> extraValues = extraBinding.getValues();
            if (records == null) {
                initRecords();
                for (Object value : extraValues) {
                    records.add(new Record(extraBinding.var, value));
                }
            } else {
                if (records.size() != extraValues.size()) {
                    throw new IllegalArgumentException("Existing Bridge has " + records.size()
                            + " tuples and trying to add a column of " + extraBinding.getValues().size());
                }
                Iterator<?> iterator = extraValues.iterator();
                for (Record rec : records) {
                    final Object extraValue = iterator.next();
                    rec.set(extraBinding.var, extraValue);
                }
            }
        }
    }

    @Override
    public void cartesianColumn(Binding<?> extraBinding) {
        if (extraBinding.isBound()) {
            this.vars.add(extraBinding.var);
            List<?> extraValues = extraBinding.getValues();
            if (records == null) {
                initRecords();
                for (Object value : extraValues) {
                    records.add(new Record(extraBinding.var, value));
                }
            } else {
                for (Record rec : new ArrayList<Record>(records)) {
                    this.remove(rec);
                    for (final Object value : extraValues) {
                        final Record cloned = new Record(rec).set(extraBinding.var, value);
                        this.add(cloned);
                    }
                }
            }
        }
    }

    @Override
    public boolean isBound(Var<?> theVar) {
        return this.vars.contains(theVar);
        // if (this.records == null || this.records.size() == 0) {
        // return false;
        // }
        // // Take the first record that has theVar bound to a value
        // for (Record rec : this.records) {
        // if (rec.get(theVar) != null) {
        // return true;
        // }
        // }
        // return false;
    }

    @Override
    public List<Record> fetch(Var<?>... theVars) {
        initRecords();
        // if (theVars.length != 1) {
        // return null;
        // }
        // if (theVars[0] != binding0.var) {
        // return null;
        // }
        // if (binding0.isFree()) {
        // return null;
        // }

        // Return a cloned list so that iterating on it will not forbid
        // removing
        // elements of our original list
        // Otherwise, we receive ConcurrentModificationException
        return new ArrayList<Record>(records);
    }

    private List<Record> initRecords() {
        if (records == null) {
            records = new LinkedList<Record>();
        }
        return records;
    }

    @Override
    public <T> T get(Record toRead, Var<T> theVar) {
        return toRead.get(theVar);
    }

    @Override
    public <T> Record set(Record toWrite, Var<T> theVar, T theValue) {
        this.vars.add(theVar);
        toWrite.set(theVar, theValue);
        return toWrite;
    }

    @Override
    public void remove(Record toRemove) {
        if (this.records != null) {
            this.records.remove(toRemove);
        }
    }

    @Override
    public Record add(Record record) {
        initRecords();
        this.vars.addAll(record.vars());
        this.records.add(record);
        return record;
    }

    public <T> Record add(Var<T> theVar, T theValue) {
        this.vars.add(theVar);
        initRecords();
        Record record = new Record(theVar, theValue);
        this.records.add(record);
        return record;
    }

    // ---------------------------------------------------------------
    // Operations to manipulate bridges
    // ---------------------------------------------------------------

    private static void retainOnly(Bridge bridge, Var<?> var, Set<?> toRetain) {
        for (final Record rec : bridge.fetch(var)) {
            if (!toRetain.contains(rec.get(var))) {
                bridge.remove(rec);
            }
        }
    }

    @Override
    public void cartesianAnd(BridgeImpl merging) {

        final Set<Var<?>> thisVars = this.vars();
        final Set<Var<?>> mergingVars = merging.vars();
        // If any mergingVar exists in this bridge, we will now "AND", ie remove
        // all
        // values from this bridge that do not belong
        for (final Var<?> mergingVar : mergingVars) {
            if (thisVars.contains(mergingVar)) {
                final HashSet<?> mergingValues = new HashSet<Object>(merging.valuesOf(mergingVar));
                // In this bridge, if the same variable is not included in the
                // merging values, remove
                retainOnly(this, mergingVar, mergingValues);
            }
        }
        // Now reversely: remove off the merging Bridge
        for (final Var<?> thisVar : thisVars) {
            if (mergingVars.contains(thisVar)) {
                final HashSet<?> thisValues = new HashSet<Object>(this.valuesOf(thisVar));
                // In this bridge, if the same variable is not included in the
                // merging values, remove
                retainOnly(merging, thisVar, thisValues);
            }
        }
        // All common vars are now ANDed
        // Do the cartesian product
        // Cartesian product
        final List<Record> fetch = fetch();
        if (fetch.isEmpty()) {
            for (final Record rec : merging.fetch()) {
                final Record added = new Record(rec);
                this.add(added);
            }
        } else {
            for (final Record rec : fetch) {
                this.remove(rec);
                for (final Record mer : merging.fetch()) {
                    final Record added = new Record(rec);
                    for (Var<?> v : mergingVars) {
                        added.set(v, mer.get(v));
                    }
                    this.add(added);
                }
            }
        }
        this.vars.addAll(merging.vars);
    }

    private List<?> valuesOf(Var<?> thatVar) {
        if (this.records == null || this.records.isEmpty()) {
            return EMPTY_LIST;
        }
        final List<Object> result = new ArrayList<Object>();
        for (final Record rec : this.records) {
            final Object value = rec.get(thatVar);
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }

    @Override
    public Set<Var<?>> vars() {
        return this.vars;
        // if (this.records == null || this.records.isEmpty()) {
        // return EMPTY_SET;
        // }
        // return records.get(0).vars();
    }

    @Override
    public <T> List<Tuple1<T>> asList(Var<T> var) {
        final List<Tuple1<T>> tuples = new ArrayList<Tuple1<T>>();
        if (records != null) {
            for (Record rec : records) {
                tuples.add(new Tuple1<T>((T) rec.get(var)));
            }
        }
        return tuples;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> asList(Var<T0> var0, Var<T1> var1) {
        final List<Tuple2<T0, T1>> tuples = new ArrayList<Tuple2<T0, T1>>();
        if (records != null) {
            for (Record rec : records) {
                tuples.add(new Tuple2<T0, T1>((T0) rec.get(var0), (T1) rec.get(var1)));
            }
        }
        return tuples;
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> asList(Var<T0> var0, Var<T1> var1, Var<T2> var2) {
        final List<Tuple3<T0, T1, T2>> tuples = new ArrayList<Tuple3<T0, T1, T2>>();
        if (records != null) {
            for (Record rec : records) {
                tuples.add(new Tuple3<T0, T1, T2>((T0) rec.get(var0), (T1) rec.get(var1), (T2) rec.get(var2)));
            }
        }
        return tuples;
    }
    
    @Override
    public String toString() {
        final String content = this.records == null ? "(empty)" : ("#" + this.records.size() + ':' + this.records);
        return "Bridge" + content;
    }


}
