package org.logic2j.predsolver.solve.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.tuple.Tuple1;

public class Solution1<T0> extends Solution {

	private Var<T0> v0;
	private List<T0> values;
	
	public Solution1(Predicate pred, Var<T0> v0) {
		super(pred);
		this.v0 = v0;
	}

	public Solution1(Predicate pred, T0 value) {
		super(pred);
		this.values = new ArrayList<T0>();
		this.values.add(value);
		throw new UnsupportedOperationException("Not impl - bug");
	}

	public List<T0> getList() {
		pred.solve(v0);
		return values;
	}
	
	public List<Tuple1<T0>> tuples() {
		return Collections.emptyList();
	}

	public Iterable<Tuple1<T0>> iterable() {
		return tuples();
	}

	@Override
	public int nbVars() {
		return 1;
	}
	
}
