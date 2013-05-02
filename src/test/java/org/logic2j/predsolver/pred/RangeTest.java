package org.logic2j.predsolver.pred;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.solve.SolverImpl;

public class RangeTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RangeTest.class);

    @Test
    public void range_X_solveXfree() {
        Predicate p = new Range(X, 4, 6);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(3, tuples.size());
        assertEquals("[<4>, <5>, <6>]", tuples.toString());
    }

}
