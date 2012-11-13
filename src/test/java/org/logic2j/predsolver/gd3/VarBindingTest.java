package org.logic2j.predsolver.gd3;

import static org.junit.Assert.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Solver;

/**
 * TODO: link two variables together. One solution (compatible with predicate
 * calculus) is equal(A, B), but from an API perspective, A.bind(B) would be
 * simpler.
 * 
 * @author Laurent
 */
public class VarBindingTest {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VarBindingTest.class);

	private Var<Integer> vInt;

	@Before
	public void setUp() {
		this.vInt = new Var<Integer>();
	}

	/**
	 * Fundamental properties of a free variable.
	 */
	@Test
	public void testFreeVar() {
		assertTrue(this.vInt.isFree());
		assertFalse(this.vInt.isBound());
		assertFalse(this.vInt.isInfinite());
		assertFalse(this.vInt.isScalar());
		assertNull(this.vInt.size());
	}

	/**
	 * Fundamental properties of a variable bound to a scalar.
	 */
	@Test
	public void testScalarVar() {
		this.vInt.setValue(10);
		assertFalse(this.vInt.isFree());
		assertTrue(this.vInt.isBound());
		assertFalse(this.vInt.isInfinite());
		assertTrue(this.vInt.isScalar());
		assertEquals(new Long(1), this.vInt.size());
		assertEquals(new Integer(10), this.vInt.getValue());
		assertNotNull(this.vInt.getValues());
		assertEquals(1, this.vInt.getValues().size());
	}

	/**
	 * Fundamental properties of a variable bound to multiple values (vector).
	 */
	@Test
	public void testVectorialVar() {
		this.vInt.setValues(1, 2, 3, 4);
		assertFalse(this.vInt.isFree());
		assertTrue(this.vInt.isBound());
		assertFalse(this.vInt.isInfinite());
		assertFalse(this.vInt.isScalar());
		assertEquals(new Long(4), this.vInt.size());
		try {
			this.vInt.getValue();
			fail();
		} catch (IllegalArgumentException e) {
			// Expected
		}
		assertNotNull(this.vInt.getValues());
		assertEquals(4, this.vInt.getValues().size());
	}

	@Test
	public void testSolveAllValuesInfinite() {
		// integer(X) is an infinite set
		Predicate pred = integer(vInt); 
		final List<Integer> solutions = new Solver().solve(pred, vInt).getList(); 
		logger.info("solved values={}", solutions);
		assertTrue(vInt.isInfinite());
	}

	// solve with one projection just gives a List
	@Test
	public void testSolveAllValuesInfinite2() {
		Predicate pred = integer(vInt);
		List<Integer> xs = new Solver().solve(pred, vInt).getList();
		logger.info("solved values={}", xs);
	}

}
