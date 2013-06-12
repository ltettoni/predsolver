package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;
import org.logic2j.predsolver.sample.LT;

public class LTTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LTTest.class);

    @Test
    public void LT1() {
        Predicate p = new LT(X, 5);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<4>, <3>, <2>, <1>, <0>]", tuples.toString());
    }

}
