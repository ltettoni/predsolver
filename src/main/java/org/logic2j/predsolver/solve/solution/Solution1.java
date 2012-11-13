package org.logic2j.predsolver.solve.solution;

import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.tuple.Tuple1;

public class Solution1<T0> extends Solution {

	public List<T0> getList() {
		return Collections.emptyList();
	}
	
	public List<Tuple1<T0>> tuples() {
		return Collections.emptyList();
	}

	@Override
	public int nbVars() {
		return 1;
	}
	
}
