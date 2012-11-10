package org.logic2j.predsolver.gd3;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.gd3.Gd3Provider.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;
import static org.logic2j.predsolver.model.Var.*;

import org.junit.Before;
import org.junit.Test;
import org.logic2j.predsolver.JdbcQuery;
import org.logic2j.predsolver.gd3.Gd3Provider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;

public class Gd3ProviderUseCaseTest {

	private final Gd3Provider GD3 = new Gd3Provider();

	private Var<Long> com;

	@Before
	public void setUp() {
		// Com is a free var of type Long
		this.com = new Var<Long>("Com");
	}

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

	@Test
	public void testCompose() {
		Var<StringBuilder> ref = new Var<StringBuilder>("R");
		Predicate pred = committeeForBalloting(com).and(ref(com, ref));
	}
	
	@Test
	public void test_search_one_of_several_values() {
		// classif(Com, ["classifA", "classifB"])
		Predicate pred = classif(com, cst("classifA", "classifB"));
	}

	@Test
	public void test_search_where_any_value_exists() {
		// Not sure this is correct
		Predicate pred = exists(classif(com, cst("classifA", "classifB")));
	}

	@Test
	public void test_search_where_any_value_does_not_exists() {
		// Not sure this is correct
		Predicate pred = not(exists(classif(com, cst("classifA", "classifB"))));
	}
	
	/**
	 * This is how we define a new predicate based on existing ones:
	 * committeeForBalloting(X) :- committee(X), classif(X, ...), classif(X, ...), active(X).
	 * @param com
	 * @return
	 */
	private Predicate committeeForBalloting(Var<Long> com) {
		return committee(com).and(classif(com, "LEVEL_MAIN", "LEVEL_SUB")).and(classif(com, "FIELD_TECHNICAL", "FIELD_POLICY")).and(active(com));
	}

}
