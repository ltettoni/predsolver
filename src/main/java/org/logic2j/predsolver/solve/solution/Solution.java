package org.logic2j.predsolver.solve.solution;

import org.logic2j.predsolver.JdbcQuery;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Cardinality;

/**
 * From a solution we can obtain:
 * - exists
 * - count
 * - iterable<Tuples>
 * - (sometimes): collection<Tuples>
 * - (sometimes): query
 * 
 * @author Laurent
 */
public abstract class Solution {

	protected Predicate pred;

	public Solution(Predicate pred) {
		this.pred = pred;
	}

	// Dimension of the solution
	public abstract int nbVars();

	// Size features of the solution
	public Cardinality cardinality() {
		return Cardinality.UNKNOWN;
	}
	
	// Size of the solution (number of results)
	public int nbSolutions() {
		return -1;
	}
	
	// TODO does this belong here?
	public JdbcQuery query() {
		return null;
	}
	
	public <T extends Iterable<?>> T iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
