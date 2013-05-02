package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.api.NonImplementedPredicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.gd3.domain.dto.core.Artefact;

public class CommonName extends NonImplementedPredicate implements Artefact.Predicate2<CharSequence> {

    public CommonName(Provider theProvider, String theName, Term[] theArguments) {
        super(null, CommonName.class.getSimpleName(), theArguments);
    }

    @Override
    public CharSequence[] forward(Artefact that) {
        return new String[] {that.getDisplay()};
    }

    @Override
    public Artefact[] reverse(CharSequence source) {
        return null;
    }

}
