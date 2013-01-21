package org.logic2j.predsolver.impl;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;

/**
 * Define base predicates that do not depend on a particular data source. Note
 * that some of them need also be defined in a data source, for example length()
 * which is supported by SQL.
 * 
 * @author Laurent
 */
public class ContextProvider implements Provider {
	public static final ContextProvider INSTANCE = new ContextProvider();


	/**
	 * True if x is an integer
	 * 
	 * @param x
	 * @return
	 */
	public static Predicate mbua(Var<? extends Number> org) {
		return new Predicate(INSTANCE, "mbua", org);
	}

}
