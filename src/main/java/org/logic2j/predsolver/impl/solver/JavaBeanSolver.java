package org.logic2j.predsolver.impl.solver;

import java.util.List;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Solver;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.api.tuple.Tuple3;

public class JavaBeanSolver implements Solver {

    @Override
    public <T0> List<Tuple1<T0>> solve(Predicate pred, Var<T0> v0) {
        
        return null;
    }

    @Override
    public <T0> List<Tuple1<T0>> solve(Predicate pred, Binding<T0> projectionBinding) {
        
        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Var<T1> v1) {
        
        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Binding<T1> v1) {
        
        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Var<T1> v1) {
        
        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1) {
        
        return null;
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2) {
        
        return null;
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1, Binding<T2> v2) {
        
        return null;
    }

}
