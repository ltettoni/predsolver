package org.logic2j.predsolver.tuple;


public class Tuple4<T0, T1, T2, T3> implements Tuple {
	private static final long serialVersionUID = 1L;

	public final T0 v0;
	public final T1 v1;
	public final T2 v2;
	public final T3 v3;

	public Tuple4(T0 v0, T1 v1, T2 v2, T3 v3) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	@Override
	public String toString() {
		return "<" + v0 + ", " + v1 + ", " + v2 + ", " + v3 + ">";
	}

}