package org.logic2j.predsolver.solve;

import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.tuple.Tuple2;
import org.logic2j.predsolver.tuple.Tuple3;
import org.logic2j.predsolver.tuple.Tuple4;

/**
 * Solving goals happens in this class. 
 * 
 * @author Laurent
 */
public class Solver {

	/**
	 * Check if a {@link Predicate} has at least one solution.
	 * @param pred
	 * @return
	 */
	public boolean check(Predicate pred) {
		return false;
	}

//	/**
//	 * Solve all solutions and bind all values by side effect into {@link Var}iables referenced by pred.
//	 * @param pred
//	 */
//	public void solveAll(Predicate pred) {
//		return;
//	}

	/**
	 * Solve all solutions of pred for the specified {@link Var}iable x (that must appear within pred),
	 * and return them as a List. No side effect.
	 * @param pred
	 * @param x
	 * @return
	 */
	public <T> List<T> solve(Predicate pred, Var<T> x) {
		return Collections.emptyList();
	}

	public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0,
			Var<T1> v1) {
		return Collections.emptyList();
	}

	public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred,
			Var<T0> v0, Var<T1> v1, Var<T2> v2) {
		return Collections.emptyList();
	}

	public <T0, T1, T2, T3> List<Tuple4<T0, T1, T2, T3>> solve(
			Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2, Var<T3> v3) {
		return Collections.emptyList();
	}

	public <T> Iterable<T> solveIterating(Predicate pred, Var<T> x) {
		List<T> data = Collections.emptyList();
		return data;
	}
}
