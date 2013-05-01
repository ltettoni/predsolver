package org.logic2j.predsolver.pred;

import org.logic2j.predsolver.model.Term;

public class Digit extends Member<Integer> {

    public Digit(Term x) {
        super("digit", x, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
