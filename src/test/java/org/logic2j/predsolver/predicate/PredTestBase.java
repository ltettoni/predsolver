package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;

import java.util.List;

import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple;

public class PredTestBase {

    public static final Integer[] INT_EVEN_0_8 = new Integer[] { 0, 2, 4, 6, 8 };
    public static final Integer[] INT_ODD_1_9 = new Integer[] { 1, 3, 5, 7, 9 };
    public static final Integer[] INT_DIGIT = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    public static final Integer[] INT_RANGE_1 = new Integer[] { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    protected final Var<Integer> X = new Var<Integer>("X");
    protected final Var<Integer> Y = new Var<Integer>("Y");
    protected final Var<Integer> Z = new Var<Integer>("Z");
    protected final Var<Integer> T = new Var<Integer>("T");
    protected final Var<Integer> U = new Var<Integer>("U");
    protected final Var<Integer> _V1 = new Var<Integer>();
    protected final Var<Integer> _V2 = new Var<Integer>();
    protected final Var<Integer> _V3 = new Var<Integer>();
    protected final Var<Integer> _V4 = new Var<Integer>();

    protected void assertEmpty(List<Tuple> filtered) {
        assertNotNull(filtered);
        assertEquals(0, filtered.size());
    }

    protected <T> void assertSingle(T tuple1, List<T> filtered) {
        assertNotNull(filtered);
        assertEquals(1, filtered.size());
        assertEquals(tuple1, filtered.get(0));
    }
}
