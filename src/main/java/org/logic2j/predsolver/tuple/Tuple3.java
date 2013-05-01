package org.logic2j.predsolver.tuple;


public class Tuple3<T0, T1, T2> implements Tuple {
	private static final long serialVersionUID = 1L;

	public final T0 v0;
	public final T1 v1;
	public final T2 v2;

	public Tuple3(T0 v0, T1 v1, T2 v2) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public String toString() {
		return "<" + v0 + ", " + v1 + ", " + v2 + ">";
	}

}