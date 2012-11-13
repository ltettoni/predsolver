package org.logic2j.predsolver.tuple;


public class Tuple1<T0> implements Tuple {
	private static final long serialVersionUID = 1L;

	public final T0 v0;

	public Tuple1(T0 v0) {
		this.v0 = v0;
	}

	@Override
	public String toString() {
		return "[" + v0 + "]";
	}

}