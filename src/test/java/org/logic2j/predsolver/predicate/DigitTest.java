package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;
import org.logic2j.predsolver.sample.Digit;

public class DigitTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DigitTest.class);

    @Test
    public void digit_X_solveXfree() {
        Predicate p = new Digit(X);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <1>, <2>, <3>, <4>, <5>, <6>, <7>, <8>, <9>]", tuples.toString());
    }

}
