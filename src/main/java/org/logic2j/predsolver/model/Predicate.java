package org.logic2j.predsolver.model;

import java.util.Arrays;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.solve.solution.Solution;

/**
 * A {@link Predicate} relate {@link Term}(s) together to express true
 * statements.
 * 
 * @author Laurent
 */
public class Predicate implements Term {

	@SuppressWarnings("unused")
	private Provider provider;
	private final String name;
	private Term terms[];

	public Predicate(Provider theProvider, String theName, Term... theArguments) {
		this.provider = theProvider;
		this.name = theName;
		this.terms = theArguments;
	}

	// -----------------
	// Logical operators (placed here for a fluent API)
	// -----------------

	public Predicate and(Predicate that) {
		return new Predicate(LogicProvider.INSTANCE, "and", this, that);
	}

	public Predicate or(Predicate that) {
		return new Predicate(LogicProvider.INSTANCE, "or", this, that);
	}

	public Predicate not(Predicate that) {
		return new Predicate(LogicProvider.INSTANCE, "not", that);
	}

	public Predicate negated() {
		return new Predicate(LogicProvider.INSTANCE, "not", this);
	}
	// -----------------
	// Logic
	// -----------------

	public boolean check() {
		throw new UnsupportedOperationException("Not implemented: check " + this);
	}

	public Solution solve(Var<?>... vars) {
		throw new UnsupportedOperationException("Not implemented: solve " + this);
	}
	
	// -----------------
	// Accessors
	// -----------------

	public String getName() {
		return name;
	}

	public Term[] getTerms() {
		return terms;
	}

	// -----------------
	// Core Object
	// -----------------

	@Override
	public String toString() {
		if (this.terms == null) {
			return this.name;
		}
		String asList = String.valueOf(Arrays.asList(this.terms));
		return this.name + '(' + asList.substring(1, asList.length() - 1) + ')';
	}

}
