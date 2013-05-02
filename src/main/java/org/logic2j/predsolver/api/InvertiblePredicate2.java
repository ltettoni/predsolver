package org.logic2j.predsolver.api;

public interface InvertiblePredicate2<T1, T2> {
    public T1[] inverse(T2 image);
}
