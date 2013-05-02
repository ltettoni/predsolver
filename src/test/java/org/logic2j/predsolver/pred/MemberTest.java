package org.logic2j.predsolver.pred;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;

import java.util.List;

import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.solve.SolverImpl;

public class MemberTest extends PredTestBase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MemberTest.class);
    

    @Test
    public void member_X_solveXfree() {
        Predicate p = member(X, 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<0>, <2>, <4>, <6>, <8>]", tuples.toString());
    }

    @Test
    public void member_X_solveXbound() {
        Predicate p = member(X, 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X.boundTo(1, 2, 3, 4));
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<2>, <4>]", tuples.toString());
    }

    @Test
    public void member_X_solveYfree() {
        Predicate p = member(X, 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y);
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void member_X_solveYbound() {
        Predicate p = member(X, 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, Y.boundTo(1, 2, 3, 4));
        logger.info("Solution: {}", tuples);
        assertEquals(5, tuples.size());
        assertEquals("[<null>, <null>, <null>, <null>, <null>]", tuples.toString());
    }

    @Test
    public void member_cst_solveXfree() {
        Predicate p = new Member<Integer>(cst(1, 2, 3, 4), 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X);
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<null>, <null>]", tuples.toString());
    }

    @Test
    public void member_cst_solveXbound() {
        Predicate p = new Member<Integer>(cst(1, 2, 3, 4), 0, 2, 4, 6, 8);
        final List<Tuple1<Integer>> tuples = new SolverImpl().solve(p, X.boundTo(7, 8, 9));
        logger.info("Solution: {}", tuples);
        assertEquals(2, tuples.size());
        assertEquals("[<null>, <null>]", tuples.toString());
    }

}
