package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.gd3.domain.dto.core.*;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Term;
import org.logic2j.predsolver.model.Var;

public class Reference extends Predicate implements Committee.Predicate2<CharSequence> {

	public Reference(Provider theProvider, String theName, Term[] theArguments) {
		super(null, Reference.class.getSimpleName(), theArguments);
	}

	public boolean apply(Committee that, Var<Committee> theArgument, Var<CharSequence> theVar) {
		return theArgument.unify(that) && theVar.unify(that.getReference());
	}

}
