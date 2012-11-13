package org.logic2j.predsolver.solve;

import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.solution.Solution1;
import org.logic2j.predsolver.solve.solution.Solution2;
import org.logic2j.predsolver.solve.solution.Solution3;

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

	// TODO
//	public Solution solve(Predicate pred) {
//		return null;
//	}
	
	public <T0> Solution1<T0> solve(Predicate pred, Var<T0> v0) {
		return new Solution1<T0>();
	}
	
	public <T0, T1> Solution2<T0, T1> solve(Predicate pred, Var<T0> v0, Var<T1> v1) {
		return new Solution2<T0, T1>();
	}
	
	public <T0, T1, T2> Solution3<T0, T1, T2> solve(Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2) {
		return new Solution3<T0, T1, T2>();
	}

}
