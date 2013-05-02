package org.logic2j.predsolver.pred;

import java.util.HashSet;
import java.util.Set;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.solve.bridge.Bridge;
import org.logic2j.predsolver.solve.bridge.BridgeImpl;
import org.logic2j.predsolver.solve.bridge.Record;

public class Or extends Predicate {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Or.class);

    public Or(Term... terms) {
        super(LogicProvider.INSTANCE, "or", terms);
    }

    @Override
    public void apply(Bridge bridge) {
        // Clone the incoming bridge, one per term of the OR
        final Bridge[] cloned = new Bridge[this.terms.length];
        for (int i = 0; i < this.terms.length; i++) {
            cloned[i] = new BridgeImpl(bridge);
        }
        // Empty the incoming bridge
        for (Record rec : bridge.fetch()) {
            bridge.remove(rec);
        }
        // Now call the terms of the OR on their local copies of the incoming
        // bridge
        for (int i = 0; i < this.terms.length; i++) {
            final Predicate predI = (Predicate) this.terms[i];
            predI.apply(cloned[i]);
            logger.info("After normal apply of {}: {}", predI, cloned);
            // And transfer the records to the incoming bridge (adding them all)
            for (Record transferred : cloned[i].fetch()) {
                bridge.add(transferred);
            }
        }
    }

    @Override
    public Set<Var<?>> publicVars() {
        Set<Var<?>> result = new HashSet<Var<?>>();
        for (Term term : terms) {
            result.addAll(((Predicate) term).publicVars());
        }
        return result;
    }
}
