package org.logic2j.predsolver.solve.solution;

import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.tuple.Tuple2;

public class Solution2<T0, T1> extends Solution {

	public List<Tuple2<T0, T1>> tuples() {
		return Collections.emptyList();
	}

	@Override
	public int nbVars() {
		return 2;
	}
}