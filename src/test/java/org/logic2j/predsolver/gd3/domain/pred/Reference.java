package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.gd3.domain.dto.core.Committee;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Term;

public class Reference extends Predicate implements Committee.Predicate2<CharSequence> {

    public Reference(Provider theProvider, String theName, Term[] theArguments) {
        super(null, Reference.class.getSimpleName(), theArguments);
    }

    @Override
    public CharSequence[] forward(Committee that) {
        return new String[] {that.getReference()};
    }

    @Override
    public Committee[] reverse(CharSequence source) {
        return null;
    }


}
