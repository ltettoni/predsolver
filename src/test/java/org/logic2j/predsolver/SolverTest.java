package org.logic2j.predsolver;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.pred.And;
import org.logic2j.predsolver.pred.Or;
import org.logic2j.predsolver.pred.PredTestBase;
import org.logic2j.predsolver.solve.Solver;
import org.logic2j.predsolver.tuple.Tuple1;
import org.logic2j.predsolver.tuple.Tuple2;

public class SolverTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SolverTest.class);

    // Copy paste this template for assertions
    // assertEquals("[<>, <>, <>, <>, <>]", tuples.toString());

    @Test
    public void and_or_123_345() {
        Predicate or123 = new Or(member(X, 1), member(X, 2), member(X, 3));
        Predicate or345 = new Or(member(X, 3), member(X, 4), member(X, 5));
        Predicate and = new And(or123, or345);
        final List<Tuple1<Integer>> tuples = new Solver().solve(and, X);
        logger.info("Solution: {}", tuples);
        assertEquals("[<3>]", tuples.toString());
    }

    @Test
    public void toto() {
        Predicate squares = digit(X).and(sqr(X, Y)).and(sqr(_V1, Y)).and(abs(_V1, Z));
        Predicate and = and(squares);
        final List<Tuple1<Integer>> tuples = new Solver().solve(and, Z);
        logger.info("Solution: {}", tuples);
        assertEquals(19, tuples.size());
    }

    @Test
    public void toto2() {
        Predicate squares = digit(X).and(sqr(X, Y)).and(sqr(_V1, Y)).and(abs(_V1, Z));
        Predicate and = and(squares);
        final List<Tuple2<Integer, Integer>> tuples = new Solver().solve(and, Y, Z);
        logger.info("Solution: {}", tuples);
        assertEquals(19, tuples.size());
    }

    @Test
    public void checkRange() {
        Predicate squares = digit(X).and(sqr(X, Y)).and(range(Y, 40, 70));
        Predicate and = and(squares);
        final List<Tuple1<Integer>> tuples = new Solver().solve(and, Y);
        logger.info("Solution: {}", tuples);
        assertEquals("[<49>, <64>]", tuples.toString());
    }

}
