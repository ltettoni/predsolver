package org.logic2j.predsolver.predicate;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.impl.solver.SolverImpl;

public class Even08Test extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Even08Test.class);

    @Test
    public void even08_X_solveXfree() {
        Predicate p = new Even08(X);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    @Test
    public void even08_X_solveXbound() {
        Predicate p = new Even08(X);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X.boundTo(1, 2, 3, 4));
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<2>, <4>]", tuples.toString());
    }

    @Test
    public void even08_X_solveYfree() {
        Predicate p = new Even08(X);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void even08_X_solveYbound() {
        Predicate p = new Even08(X);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y.boundTo(1, 2, 3, 4));
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void even08_cst_solveXfree() {
        Predicate p = new Even08(cst(1, 2, 3, 4));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<null>, <null>]", tuples.toString());
    }

    @Test
    public void even08_cst_solveXbound() {
        Predicate p = new Even08(cst(1, 2, 3, 4));
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X.boundTo(7, 8, 9));
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<null>, <null>]", tuples.toString());
    }

}
