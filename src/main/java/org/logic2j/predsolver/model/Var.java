package org.logic2j.predsolver.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A {@link Var} is the endpoint of a constraint, it can hold either zero, one,
 * or several simultaneous values, finite or infinite.
 * 
 * It is used to specify parameters to a problem, or to extract results from the
 * solution.
 * 
 * @author Laurent
 * @param <T>
 */
public class Var<T> implements Term {

	private List<T> values;
	private boolean infinite = false;
	private String name = null; // Optional name

	// Will a Var need to carry its runtime type?

	public Var() {
		super();
	}

	public Var(String name) {
		super();
		this.name = name;
	}

	/**
	 * Factory for a constant value.
	 * @param constant
	 * @return A {@link Var} whose value is bound (and fixed).
	 */
	public static <T> Var<T> cst(T... constant) {
		final Var<T> var = new Var<T>();
		var.setValues(constant);
		return var;
	}

	// Set values

	public void setValue(T theValue) {
		this.values = new ArrayList<T>(1);
		this.values.add(theValue);
	}

	public void setValues(List<T> theValues) {
		this.values = new ArrayList<T>(theValues);
	}

	public void setValues(T... theValues) {
		this.values = Arrays.asList(theValues);
	}

	public void bind(Var<T> other) {
		// Something...
	}

	// Get single or multiple

	public T getValue() {
		if (!isBound()) {
			throw new IllegalArgumentException("Term " + this + " is not bound");
		}
		if (!isScalar()) {
			throw new IllegalArgumentException("Term " + this + " is not a scalar");
		}
		return this.values.get(0);
	}

	public List<T> getValues() {
		if (!isBound()) {
			throw new IllegalArgumentException("Term " + this + " is not bound");
		}
		return this.values;
	}

	public Long size() {
		if (this.values == null) {
			return null;
		} else {
			return new Long(this.values.size());
		}
	}

	public boolean isInfinite() {
		if (this.infinite) {
			return true;
		}
		if (size() == null) {
			return false;
		}
		return size() == Long.MAX_VALUE;
	}

	public boolean isScalar() {
		if (this.values == null) {
			return false;
		}
		return size() == 1;
	}

	public boolean isBound() {
		return this.values != null;
	}

	public boolean isFree() {
		return this.values == null;
	}

	@Override
	public String toString() {
		if (name != null) {
			return name;
		}
		return "Var@" + Integer.toHexString(this.hashCode());
	}

}
