package org.logic2j.predsolver.gd3;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;
import static org.logic2j.predsolver.model.Var.*;

import org.junit.Before;
import org.junit.Test;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Solver;

/**
 * 
 * @author Laurent
 */
public class LogicProviderUseCaseTest {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LogicProviderUseCaseTest.class);
	private Var<Integer> vInt;

	@Before
	public void setUp() {
		this.vInt = new Var<Integer>();
	}

	// ------
	// should check() also bind free values? - probably yes
	// ------

	@Test
	public void testCheckOneValueValid() {
		// square(3, 9)
		Predicate pred = square(cst(3), cst(9));
		boolean checked = new Solver().check(pred);
		logger.info("check={}", checked);
		assertTrue(checked);
	}

	@Test
	public void testCheckOneValueInvalid() {
		// square(3, 8)
		Predicate pred = square(cst(3), cst(8));
		boolean checked = new Solver().check(pred);
		logger.info("check={}", checked);
		assertFalse(checked);
	}

	// ------
	// solveAll without specifying projected values - does this make sense?
	// ------

	@Test
	public void testSolveAllValuesForScalar() {
		// square(3, X)
		Predicate pred = square(cst(3), vInt);
		new Solver().solveAll(pred);
		logger.info("solved values={}", vInt);
		assertTrue(vInt.isBound());
		assertTrue(vInt.isScalar());
		assertEquals(new Integer(9), vInt.getValue());
	}

	// @Test
	// public void testSolveAllValues2() {
	// Predicate pred = string(vDbl, vStr);
	// List<Tuple2<Double, String>> xs = new Solver().solve(pred, vDbl, vStr);
	// logger.info("solved values=" + xs);
	// }

	@Test
	public void testSolveIteratingOne() {
		Predicate pred = square(cst(3), vInt);
		for (Integer value : new Solver().solveIterating(pred, vInt)) {
			logger.info("solved value={}", value);
		}
	}

}
