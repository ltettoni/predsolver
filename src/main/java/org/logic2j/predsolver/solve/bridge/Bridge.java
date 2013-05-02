package org.logic2j.predsolver.solve.bridge;

import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.api.tuple.Tuple3;

public interface Bridge {

    public boolean isBound(Var<?> theVar);

    public List<Record> fetch(Var<?>... theVars);

    public Record add(Record rec);

    public <T> T get(Record toRead, Var<T> theVar);

    public <T> Record set(Record toWrite, Var<T> theVar, T theValue);

    public void remove(Record toRemove);

    public void parallelColumn(Binding<?> extraBinding);

    public void cartesianAnd(BridgeImpl that);

    public void cartesianColumn(Binding<?> extraBinding);

    public Set<Var<?>> vars();

    public <T> List<Tuple1<T>> asList(Var<T> var);
    public <T0, T1> List<Tuple2<T0, T1>> asList(Var<T0> var0, Var<T1> var1);
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> asList(Var<T0> var0, Var<T1> var1, Var<T2> var2);

}
