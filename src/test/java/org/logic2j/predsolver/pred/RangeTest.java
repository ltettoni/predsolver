package org.logic2j.predsolver.pred;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.solve.Solver;
import org.logic2j.predsolver.tuple.Tuple1;

public class RangeTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RangeTest.class);

    @Test
    public void range_X_solveXfree() {
        Predicate p = new Range(X, 4, 6);
        final List<Tuple1<Integer>> tuples = new Solver().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(3, tuples.size());
        assertEquals("[<4>, <5>, <6>]", tuples.toString());
    }

}
