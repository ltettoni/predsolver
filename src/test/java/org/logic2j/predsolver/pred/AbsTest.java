package org.logic2j.predsolver.pred;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.sample.Abs;
import org.logic2j.predsolver.solve.SolverImpl;

public class AbsTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AbsTest.class);

    @Test
    public void abs_sovleY() {
        Predicate p = new Abs(cst(1, -2, 0, -3, 3), Y);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<1>, <2>, <0>, <3>, <3>]", tuples.toString());
    }

    @Test
    public void abs_sovleX() {
        Predicate p = new Abs(X, cst(0, 1, 2, -3, -4));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <1>, <2>, <-1>, <-2>]", tuples.toString());
//        assertEquals("[<0>, <1>, <-1>, <2>, <-2>]", tuples.toString());
    }

}
