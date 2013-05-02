package org.logic2j.predsolver.sample;

import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.predicate.Member;

public class Digit extends Member<Integer> {

    public Digit(Term x) {
        super(LogicProvider.INSTANCE, "digit", x, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
