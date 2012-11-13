package org.logic2j.predsolver.solve;

public enum Cardinality {
	/**
	 * Not determined (yet)
	 */
	UNKNOWN,
	/**
	 * Cannot be determined by the current resolution algorithms
	 */
	UNDECIDEABLE,
	/**
	 * No solution
	 */
	EMPTY,
	/**
	 * One solution
	 */
	SINGLE,
	/**
	 * More than one but less than infinite
	 */
	MULTIPLE,
	/**
	 * Infinite
	 */
	INFINITE;
	
	public boolean exists() {
		return this==SINGLE || this==MULTIPLE || this==INFINITE;
	}
	
}
