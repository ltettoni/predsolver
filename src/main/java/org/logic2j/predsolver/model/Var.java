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

	private String name = null; // Optional name
	private List<T> values;

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
	 * 
	 * @param constant
	 * @return A {@link Var} whose value is bound (and fixed).
	 */
	public static <T> Var<T> cst(T... constant) {
		// Provide some name - helpful for tracing
		final String name;
		if (constant.length == 1) {
			name = "cst(" + constant[0] + ')';
		} else {
			name = null;
		}
		final Var<T> var = new Var<T>(name);
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

	// -----------------
	// Core Object
	// -----------------

	@Override
	public String toString() {
		if (name != null) {
			return name;
		}
		return "Var@" + Integer.toHexString(this.hashCode());
	}

	public boolean unify(T display) {
		// TODO Auto-generated method stub
		return false;
	}

}
