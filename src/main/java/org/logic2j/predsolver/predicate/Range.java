package org.logic2j.predsolver.predicate;

import org.logic2j.predsolver.api.Term;

public class Range extends Member<Integer> {

    public Range(Term x, int min, int max) {
        super("range", x, arrayRange(min, max));
    }

    private static Integer[] arrayRange(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Range arguments must have min <= max");
        }
        int nbr = max - min + 1;
        final Integer[] range = new Integer[nbr];
        for (int i=0; i<nbr; i++) {
            range[i] = min+i;
        }
        return range;
    }
}
