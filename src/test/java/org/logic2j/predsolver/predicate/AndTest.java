package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;
import org.logic2j.predsolver.sample.Fail;
import org.logic2j.predsolver.sample.True;

public class AndTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AndTest.class);

    @Test
    public void and_empty() {
        Predicate and = new And();
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(0, tuples.size());
    }

    @Test
    public void and_single() {
        Predicate and = new And(new Even08(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }
    
    @Test
    public void and_single_evenX_evenX_unboundX() {
        Predicate and = new And(new Even08(X), new Even08(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    @Test
    public void and_single_evenX_oddX_unboundX() {
        Predicate and = new And(new Even08(X), new Odd19(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(0, tuples.size());
    }

    /**
     * Cartesian product ^2
     */
    @Test
    public void and_single_evenX_evenY_unboundXY() {
        Predicate and = new And(new Even08(X), new Even08(Y));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(25, tuples.size());
    }
    
    /**
     * Cartesian product ^3
     */
    @Test
    public void and_single_evenX_evenY_evenZ_unboundXYZ() {
        Predicate and = new And(new Even08(X), new Even08(Y), new Even08(Z));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(125, tuples.size());
    }

    // This AND provides more values because of abs in inverse mode
    @Test
    public void and_single_evenX_absZY_unboundXY() {
        Predicate and = new And(new Even08(X), new Abs(Z, X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(9, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    // This AND provides more values because of abs in inverse mode
    @Test
    public void and_single_absXY_absXY_unboundXYZ() {
        Predicate and = new And(new Abs(Y, cst(-1, 2, 3)), new Abs(Z, Y));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, Z);
        logger.info("Solution: {}", tuples);
        assertEquals(4, tuples.size());
        assertEquals("[<2>, <3>, <-2>, <-3>]", tuples.toString());
    }

    @Test
    public void and_single_evenX_evenX_evenX_evenX_unboundX() {
        Predicate and = new And(new Even08(X), new Even08(X), new Even08(X), new Even08(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    @Test
    public void and_single_evenX_even123456_evenX_evenX_unboundX() {
        Predicate and = new And(new Even08(X), new Even08(cst(1, 2, 3, 4)));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <0>, <2>, <2>, <4>, <4>, <6>, <6>, <8>, <8>]", tuples.toString());
    }


    @Test
    public void and_and_right() {
        Predicate pred = new And(new Range(X, 1, 3), new And(new Range(X, 5, 7), new Range(X, 10, 12)));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[]", tuples.toString());
    }

    @Test
    public void and_and_left() {
        Predicate pred = new And(new And(new Range(X, 1, 3), new Range(X, 5, 7)), new Range(X, 10, 12));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[]", tuples.toString());
    }

    public void and_and_sameright() {
        Predicate pred = new And(new Even08(X), new And(new Even08(X), new Even08(X)));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }
    
    public void and_and_sameleft() {
        Predicate pred = new And(new And(new Even08(X), new Even08(X)), new Even08(X));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }
    
    @Test
    public void and_true() throws Exception {
        Predicate pred = new And(new Even08(X), new True());
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }
    
    @Test
    public void and_fail() throws Exception {
        Predicate pred = new And(new Even08(X), new Fail());
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(pred, X);
        logger.info("Solution: {}", tuples);
        assertEquals(0, tuples.size());
    }
    
    
}
