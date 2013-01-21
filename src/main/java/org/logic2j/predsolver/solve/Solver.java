package org.logic2j.predsolver.solve;

import java.lang.reflect.Method;

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
   private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Solver.class);
   
	/**
	 * Check if a {@link Predicate} has at least one solution.
	 * @param pred
	 * @return
	 */
	public boolean check(Predicate pred) {
		return pred.check();
	}

	// TODO Does this make sense to solve without projecting vars - we would just count solutions?
//	public Solution solve(Predicate pred) {
//		return null;
//	}
	
	public <T0> Solution1<T0> solve(Predicate pred, Var<T0> v0) {
		for (Method method : pred.getClass().getMethods()) {
			if (! method.getName().equals("apply")) {
				continue;
			}
			logger.info("Method: {}", method);
			// Then reflectively invoke???
		}
		return new Solution1<T0>(pred, v0);
	}
	
	public <T0, T1> Solution2<T0, T1> solve(Predicate pred, Var<T0> v0, Var<T1> v1) {
		return new Solution2<T0, T1>(pred);
	}
	
	public <T0, T1, T2> Solution3<T0, T1, T2> solve(Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2) {
		return new Solution3<T0, T1, T2>(pred);
	}

}
