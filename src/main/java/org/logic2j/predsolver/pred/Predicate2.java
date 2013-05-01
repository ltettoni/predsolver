package org.logic2j.predsolver.pred;

import java.util.List;

import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.model.Filter2;
import org.logic2j.predsolver.model.Forward2;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Reverse2;
import org.logic2j.predsolver.model.Term;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.bridge.Bridge;
import org.logic2j.predsolver.solve.bridge.Record;

public abstract class Predicate2<T1, T2> extends Predicate implements Filter2<T1, T2>, Forward2<T1, T2>, Reverse2<T1, T2> {

    public Predicate2(Provider theProvider, String theName, Term x, Term y) {
        super(theProvider, theName, x, y);
    }

    public Predicate2(Term x, Term y) {
        super(LogicProvider.INSTANCE, "square", x, y);
    }

    @Override
    public final void apply(Bridge bridge) {
        final Var<T1> x = var(0);
        final Var<T2> y = var(1);

        boolean xBound = isBound(bridge, 0);
        boolean yBound = isBound(bridge, 1);
        if (xBound && !yBound) {
            // Direct function
            List<Record> fetched = fetch(bridge, 0);
            for (Record rec : fetched) {
                final T1 vx = bridge.get(rec, x);
                final T2[] ys = forward(vx);
                final int nbr = ys.length;
                if (nbr == 0) {
                    bridge.remove(rec);
                } else {
                    bridge.set(rec, y, ys[0]);
                    for (int i = 1; i < nbr; i++) {
                        final Record cloned = new Record(rec).set(x, ys[i]);
                        bridge.add(cloned);
                    }
                }
            }
        } else if (!xBound && yBound) {
            // Inverse function
            for (Record rec : fetch(bridge, 1)) {
                final T2 vy = bridge.get(rec, y);
                final T1[] xs = reverse(vy);
                final int nbr = xs.length;
                if (nbr == 0) {
                    bridge.remove(rec);
                } else {
                    bridge.set(rec, x, xs[0]);
                    for (int i = 1; i < nbr; i++) {
                        final Record cloned = new Record(rec).set(x, xs[i]);
                        bridge.add(cloned);
                    }
                }
            }
        } else if (xBound && yBound) {
            // Filter function
            for (Record rec : fetch(bridge, 0, 1)) {
                final T1 vx = bridge.get(rec, x);
                final T2 vy = bridge.get(rec, y);
                if (!filter(vx, vy)) {
                    bridge.remove(rec);
                }
            }
        } else {
            // Generate function (both free vars)
            throw new IllegalArgumentException("square/2 cannot resolve on 2 free bindings");
        }
    }
}
