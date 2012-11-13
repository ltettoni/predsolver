package org.logic2j.predsolver.solve.solution;

import org.logic2j.predsolver.JdbcQuery;
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
