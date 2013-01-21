package org.logic2j.predsolver.solve.solution;

import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.tuple.Tuple4;

public class Solution4<T0, T1, T2, T3> extends Solution {

	public Solution4(Predicate pred) {
		super(pred);
	}

	public List<Tuple4<T0, T1, T2, T3>> tuples() {
		return Collections.emptyList();
	}

	public Iterable<Tuple4<T0, T1, T2, T3>> iterable() {
		return tuples();
	}

	@Override
	public int nbVars() {
		return 4;
	}
}
