package org.logic2j.predsolver.api;

public interface FilteringPredicate2<T1, T2> {

    public boolean filter(T1 x, T2 y);
}
