package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.gd3.domain.dto.core.Artefact;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Term;
import org.logic2j.predsolver.model.Var;

public class CommonName extends Predicate implements Artefact.Predicate2<CharSequence> {

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
