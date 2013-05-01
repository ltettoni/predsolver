package org.logic2j.predsolver.gd3;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.logic2j.predsolver.JdbcQuery;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Solver;
import org.logic2j.predsolver.tuple.Tuple2;

import static org.junit.Assert.assertEquals;
import static org.logic2j.predsolver.gd3.Gd3Provider.*;
import static org.logic2j.predsolver.impl.LogicProvider.exists;
import static org.logic2j.predsolver.impl.LogicProvider.not;
import static org.logic2j.predsolver.model.Binding.cst;

public class Gd3ProviderUseCaseTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Gd3ProviderUseCaseTest.class);

    private final Gd3Provider GD3 = new Gd3Provider();

    private Var<Long> com;

    @Before
    public void setUp() {
        // Com is a free var of type Long
        this.com = new Var<Long>("Com");
    }

    @Ignore("lexical sample only ")
    @Test
    public void solvingCanGenerateJdbcQuery() {
        // committee(Com)
        Predicate pred = committee(com);
        // select Com from GD3 where committee(Com)
        JdbcQuery query = GD3.solveAsQuery(pred, com);
        //
        assertEquals("some SQL", query.getSql());
        assertEquals(0, query.getParametersArray().length);
    }

    @Ignore("lexical sample only ")
    @Test
    public void testLogicalAnd() {
        Var<String> ref = new Var<String>("R");
        // ref(Com, Ref), committee(Com)
        Predicate pred = ref(com, ref).and(committee(com));
        // select Com, Ref from GD3 where ref(Com, Ref), committee(Com)
        JdbcQuery query = GD3.solveAsQuery(pred, com, ref);
        //
        assertEquals("some SQL", query.getSql());
        assertEquals(0, query.getParametersArray().length);
        assertEquals(2, query.getNumberOfProjections());
    }

    @Ignore("lexical sample only ")
    @Test
    public void test_extracting_solutions() {
        Var<String> ref = new Var<String>("R");
        // ref(Com, Ref), committee(Com)
        Predicate pred = committee(com).and(ref(com, ref));
        for (Tuple2<Long, String> pair : new Solver().solve(pred, com, ref)) {
            logger.info("Committee #{} has ref: {}", pair.v0, pair.v1);
        }
    }

    @Test
    public void test_method_that_composes_several_predicates() {
        Var<StringBuilder> ref = new Var<StringBuilder>("R");
        @SuppressWarnings("unused")
		Predicate pred = committeeForBalloting(com).and(ref(com, ref));
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
        return committee(com).and(classif(com, "LEVEL_MAIN", "LEVEL_SUB")).and(classif(com, "FIELD_TECHNICAL", "FIELD_POLICY"))
                .and(active(com));
    }

    @Test
    public void test_search_one_of_several_values() {
    	@SuppressWarnings("unused")
		Predicate pred = classif(com, cst("classifA", "classifB"));
    }

    // -----------------------------------------------------------
    // Higher-order (exists, forall)
    // -----------------------------------------------------------

    @Test
    public void test_search_where_any_value_exists() {
        // Not sure this is correct
    	@SuppressWarnings("unused")
		Predicate pred = exists(classif(com, cst("classifA", "classifB")));
    }

    @Test
    public void test_search_where_any_value_does_not_exists() {
        // Not sure this is correct
    	@SuppressWarnings("unused")
		Predicate pred = not(exists(classif(com, cst("classifA", "classifB"))));
    }

}
