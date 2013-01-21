package org.logic2j.predsolver.solve.solution;

import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.tuple.Tuple3;

public class Solution3<T0, T1, T2> extends Solution {

	public Solution3(Predicate pred) {
		super(pred);
	}

	public List<Tuple3<T0, T1, T2>> tuples() {
		return Collections.emptyList();
	}

	public Iterable<Tuple3<T0, T1, T2>> iterable() {
		return tuples();
	}

	@Override
	public int nbVars() {
		return 3;
	}
}
