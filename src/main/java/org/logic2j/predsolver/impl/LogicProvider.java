package org.logic2j.predsolver.impl;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.solution.Solution;
import org.logic2j.predsolver.solve.solution.Solution1;

/**
 * Define base predicates that do not depend on a particular data source. Note
 * that some of them need also be defined in a data source, for example length()
 * which is supported by SQL.
 * 
 * @author Laurent
 */
public class LogicProvider implements Provider {
	public static final LogicProvider INSTANCE = new LogicProvider();

	/**
	 * True if y is the square of x
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static <T extends Number> Predicate square(final Var<T> x, final Var<T> y) {
		return new Predicate(INSTANCE, "sqr", x, y) {
			@Override
			public boolean check() {
				if (! (x.isBound() && y.isBound())) {
					return false;
				}
			final double vx = x.getValue().doubleValue();
			final double vy = y.getValue().doubleValue();
				return vx*vx == vy;
			}
			
			@Override
			public Solution solve(Var<?>... vars) {
				if (vars.length==1) {
					if (vars[0]==y && x.isBound()) {
						final double vx = x.getValue().doubleValue();
						return new Solution1<Number>(this, vx*vx);
					}
				}
				return super.solve(vars);
			}
		};
		
		
	}

	/**
	 * True if x is an integer
	 * 
	 * @param x
	 * @return
	 */
	public static <T> Predicate integer(Var<T> x) {
		return new Predicate(INSTANCE, "integer", x);
	}

	/**
	 * Equivalence between a variable value(s) and string representation(s)
	 * 
	 * @param x
	 * @param s
	 * @return
	 */
	public static <T> Predicate string(Var<T> x, Var<String> s) {
		return new Predicate(INSTANCE, "string", x, s);
	}

	public static <T> Predicate validUrl(Var<? extends CharSequence> s) {
		return new Predicate(INSTANCE, "validUrl", s);
	}

	
	public static Predicate exists(Predicate pred) {
		return new Predicate(INSTANCE, "exists", pred);
	}

	public static Predicate not(Predicate pred) {
		return new Predicate(INSTANCE, "not", pred);
	}
}
