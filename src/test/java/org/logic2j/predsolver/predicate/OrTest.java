package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;
import org.logic2j.predsolver.predicate.Or;
import org.logic2j.predsolver.predicate.Range;
import org.logic2j.predsolver.sample.Even08;
import org.logic2j.predsolver.sample.Fail;
import org.logic2j.predsolver.sample.Odd19;
import org.logic2j.predsolver.sample.True;

public class OrTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OrTest.class);

    @Test
    public void or_empty() {
        Predicate or = new Or();
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, X);
        logger.info("Solution: {}", tuples);
        assertEquals(0, tuples.size());
    }

    @Test
    public void or_single() {
        Predicate or = new Or(new Even08(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddY_unboundZ() {
        Predicate or = new Or(new Even08(X), new Odd19(Y));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, Z);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddY_unboundX() {
        Predicate or = new Or(new Even08(X), new Odd19(Y));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, X);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>, <null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddY_unboundY() {
        Predicate or = new Or(new Even08(X), new Odd19(Y));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>, <1>, <3>, <5>, <7>, <9>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddX_unboundZ() {
        Predicate or = new Or(new Even08(X), new Odd19(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, Z);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddX_unboundX() {
        Predicate or = new Or(new Even08(X), new Odd19(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, X);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>, <1>, <3>, <5>, <7>, <9>]", tuples.toString());
    }

    @Test
    public void or_single_evenX_oddX_Xbound() {
        Predicate or = new Or(new Even08(X), new Odd19(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(or, X.boundTo(4, 5, 6, 7, 10, 11));
        logger.info("Solution: {}", tuples);
        assertEquals(4, tuples.size());
        assertEquals("[<4>, <6>, <5>, <7>]", tuples.toString());
    }

    @Test
    public void or_or_right() {
        Predicate pred = new Or(new Range(X, 1, 3), new Or(new Range(X, 5, 7), new Range(X, 10, 12)));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[<1>, <2>, <3>, <5>, <6>, <7>, <10>, <11>, <12>]", tuples.toString());
    }

    @Test
    public void or_or_left() {
        Predicate pred = new Or(new Or(new Range(X, 1, 3), new Range(X, 5, 7)), new Range(X, 10, 12));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[<1>, <2>, <3>, <5>, <6>, <7>, <10>, <11>, <12>]", tuples.toString());
    }

    @Test
    public void or_or_Y() {
        Predicate pred = new Or(new Or(new Range(X, 1, 3), new Range(Y, 5, 7)), new Range(X, 10, 12));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[<1>, <2>, <3>, <null>, <null>, <null>, <10>, <11>, <12>]", tuples.toString());
    }

    @Test
    public void or_true() throws Exception {
        Predicate pred = new Or(new Even08(X), new True());
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[<0>, <2>, <4>, <6>, <8>, <null>]", tuples.toString());
    }

    @Test
    public void or_fail() throws Exception {
        Predicate pred = new Or(new Even08(X), new Fail());
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }
}