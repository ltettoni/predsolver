package org.logic2j.predsolver.tuple;


public class Tuple2<T0, T1> implements Tuple {
	private static final long serialVersionUID = 1L;

	public final T0 v0;
	public final T1 v1;

	public Tuple2(T0 v0, T1 v1) {
		this.v0 = v0;
		this.v1 = v1;
	}

	@Override
	public String toString() {
		return "<" + v0 + ", " + v1 + ">";
	}

}