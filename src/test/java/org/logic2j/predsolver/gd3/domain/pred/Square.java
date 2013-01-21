package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Term;
import org.logic2j.predsolver.model.Var;

public class Square extends Predicate {

	public Square(Provider theProvider, String theName, Term... theArguments) {
		super(theProvider, theName, theArguments);
	}
	
	public boolean apply(Number that, Var<Number> arg0, Var<Number> arg1) {
		return true;
	}

}
