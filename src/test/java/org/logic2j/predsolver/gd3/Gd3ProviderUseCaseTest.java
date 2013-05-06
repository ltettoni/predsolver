package org.logic2j.predsolver.gd3;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.api.Binding.*;
import static org.logic2j.predsolver.gd3.Gd3Provider.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Solver;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.gd3.domain.dto.core.Committee;
import org.logic2j.predsolver.impl.JdbcQuery;
import org.logic2j.predsolver.impl.solver.JavaBeanSolver;
import org.logic2j.predsolver.impl.solver.JdbcSolver;
import org.logic2j.predsolver.impl.solver.SolverImpl;

@SuppressWarnings("unused")
public class Gd3ProviderUseCaseTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Gd3ProviderUseCaseTest.class);

    private final Gd3Provider GD3 = Gd3Provider.INSTANCE;

    private final Var<Long> Com = new Var<Long>("Com");
    private final Var<Integer> X = new Var<Integer>("X");
    private final Var<Integer> Y = new Var<Integer>("Y");
    private final Var<Integer> Z = new Var<Integer>("Z");
    private final Var<String> Group = new Var<String>("Group");

    // -----------------------------------------------------------
    // Simplest DB tests
    // -----------------------------------------------------------

    @Test
    public void committees_from_collection() {
        List<Object> aCollection = new ArrayList<Object>();
        aCollection.add(new Committee());
        //
        Solver solver = new JavaBeanSolver();
        Predicate pred = GD3.committee(Com);
        List<Tuple1<Long>> tuples = solver.solve(pred, Com);
        logger.info("Solution: {}", tuples);
    }

    @Test
    public void committees_from_database() {
        DataSource dataSource = null;
        Solver solver = new JdbcSolver(dataSource);
        //
        Predicate pred = GD3.committee(Com);
        List<Tuple1<Long>> tuples = solver.solve(pred, Com.boundTo(22L));
        logger.info("Solution: {}", tuples);
    }

    @Ignore("lexical sample only")
    @Test
    public void solvingCanGenerateJdbcQuery() {
        // committee(Com)
        Predicate pred = GD3.committee(Com);
        // select Com from GD3 where committee(Com)
        JdbcQuery query = GD3.solveAsQuery(pred, Com);
        //
        assertEquals("some SQL", query.getSql());
        assertEquals(0, query.getParametersArray().length);
    }

    @Ignore("lexical sample only")
    @Test
    public void testLogicalAnd() {
        Var<String> ref = new Var<String>("R");
        // ref(Com, Ref), committee(Com)
        Predicate pred = ref(Com, ref).and(GD3.committee(Com));
        // select Com, Ref from GD3 where ref(Com, Ref), committee(Com)
        JdbcQuery query = GD3.solveAsQuery(pred, Com, ref);
        //
        assertEquals("some SQL", query.getSql());
        assertEquals(0, query.getParametersArray().length);
        assertEquals(2, query.getNumberOfProjections());
    }

    @Ignore("lexical sample only")
    @Test
    public void test_extracting_solutions() {
        Var<String> ref = new Var<String>("R");
        // ref(Com, Ref), committee(Com)
        Predicate pred = GD3.committee(Com).and(ref(Com, ref));
        for (Tuple2<Long, String> pair : new SolverImpl().solve(pred, Com, ref)) {
            logger.info("Committee #{} has ref: {}", pair.v0, pair.v1);
        }
    }

    @Test
    public void test_method_that_composes_several_predicates() {
        Var<StringBuilder> ref = new Var<StringBuilder>("R");
        Predicate pred = committeeForBalloting(Com).and(ref(Com, ref));
    }

    /**
     * This is how we define a new predicate based on existing ones:
     * committeeForBalloting(X) :- committee(X), classif(X, ...), classif(X,
     * ...), active(X).
     * 
     * @param com
     * @return
     */
    private Predicate committeeForBalloting(Var<Long> com) {
        return GD3.committee(com).and(classif(com, "LEVEL_MAIN", "LEVEL_SUB")).and(classif(com, "FIELD_TECHNICAL", "FIELD_POLICY"))
                .and(active(com));
    }

    @Test
    public void test_search_one_of_several_values() {
        Predicate pred = classif(Com, cst("classifA", "classifB"));
    }

    // -----------------------------------------------------------
    // Mixing non-DB and DB predicates
    // -----------------------------------------------------------

    @Test
    public void mixingNonDbWithDbPredicates() {
        SessionProvider mySession = SessionProvider.INSTANCE;
        Gd3Provider db = Gd3Provider.INSTANCE;
        //
        Predicate pred = mySession.authInGroup(Group);
        // Predicate pred =
        // mySession.mbuaOf(X).and(db.committee(Com).and(db.owner(Com, X)));
        final List<Tuple1<String>> tuples = new SolverImpl().solve(pred, Group);
        logger.info("Solution: {}", tuples);
    }

    // -----------------------------------------------------------
    // Higher-order (exists, forall)
    // -----------------------------------------------------------

    @Test
    public void test_search_where_any_value_exists() {
        // Not sure this is correct
        Predicate pred = exists(classif(Com, cst("classifA", "classifB")));
    }

    @Test
    public void test_search_where_any_value_does_not_exists() {
        // Not sure this is correct
        Predicate pred = not(exists(classif(Com, cst("classifA", "classifB"))));
    }

}
