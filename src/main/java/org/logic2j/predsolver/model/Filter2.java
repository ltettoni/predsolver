package org.logic2j.predsolver.model;

public interface Filter2<T1, T2> {

    public boolean filter(T1 x, T2 y);
}
