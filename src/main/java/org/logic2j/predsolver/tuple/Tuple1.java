package org.logic2j.predsolver.tuple;


public class Tuple1<T0> implements Tuple {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v0 == null) ? 0 : v0.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple1<?> other = (Tuple1<?>) obj;
		if (v0 == null) {
			if (other.v0 != null)
				return false;
		} else if (!v0.equals(other.v0))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	public final T0 v0;

	public Tuple1(T0 v0) {
		this.v0 = v0;
	}

	@Override
	public String toString() {
		return "<" + v0 + ">";
	}

	
	
}