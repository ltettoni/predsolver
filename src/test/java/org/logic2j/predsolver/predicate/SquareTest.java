package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;

public class SquareTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SquareTest.class);

    @Test
    public void square_sovleXbound() {
        Predicate p = new Square(X, Y);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X.boundTo(PredTestBase.INT_DIGIT));
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <1>, <2>, <3>, <4>, <5>, <6>, <7>, <8>, <9>]", tuples.toString());
    }

    @Test
    public void square_cst_solveY() {
        Predicate p = new Square(cst(PredTestBase.INT_DIGIT), Y);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(10, tuples.size());
        assertEquals("[<0>, <1>, <4>, <9>, <16>, <25>, <36>, <49>, <64>, <81>]", tuples.toString());
    }

    @Test
    public void square_freeVars_sovleY() {
        Predicate p = new Square(X, cst(PredTestBase.INT_DIGIT));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(7, tuples.size());
//        assertEquals("[<0>, <1>, <-1>, <2>, <-2>, <3>, <-3>]", tuples.toString());
        assertEquals("[<0>, <1>, <2>, <3>, <-1>, <-2>, <-3>]", tuples.toString());
    }

    @Test
    public void square_freeVars_sovleXY() {
        Predicate p = new Square(cst(PredTestBase.INT_DIGIT), cst(PredTestBase.INT_DIGIT));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(4, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>]", tuples.toString());
    }

}
