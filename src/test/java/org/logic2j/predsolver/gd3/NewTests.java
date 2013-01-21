package org.logic2j.predsolver.gd3;

import static org.logic2j.predsolver.impl.ContextProvider.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;
import static org.logic2j.predsolver.gd3.Gd3Provider.*;
import static org.logic2j.predsolver.model.Var.*;

import org.junit.Test;
import org.logic2j.predsolver.gd3.domain.pred.Square;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Solver;

public class NewTests {

	@Test
	public void test1() {
		Var<Integer> y = new Var<Integer>();
		Predicate pred = new Square(null, "square", cst(3), y);
		new Solver().solve(pred, y);
	}

	@Test
	public void mixing_context_db_logic() throws Exception {
		Var<Integer> X = new Var<Integer>();
		Var<Integer> C = new Var<Integer>();
		Var<String> U = new Var<String>();
		new Solver().solve(mbua(X).and(owner(C, X)).and(url(C, U)).and(validUrl(U)), C);
	}
}
