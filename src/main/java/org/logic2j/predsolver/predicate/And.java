package org.logic2j.predsolver.predicate;

import java.util.HashSet;
import java.util.Set;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.impl.solver.SolverImpl;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.impl.solver.bridge.BridgeImpl;
import org.logic2j.predsolver.impl.solver.bridge.LoggingBridge;

public class And extends Predicate {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(And.class);

    public And(Term... terms) {
        super(LogicProvider.INSTANCE, "and", terms);
    }

    @Override
    public void apply(Bridge bridge) {
        if (this.terms.length == 1) {
            // Not an "and" but a single predicate
            ((Predicate) this.terms[0]).apply(bridge);
            return;
        }
        for (int i = 0; i < this.terms.length; i++) {
            final Predicate predI = (Predicate) this.terms[i];
            boolean applyOnParentBridge = predI instanceof And;

            if (!applyOnParentBridge) {
                final Set<Var<?>> bridgeVars = bridge.vars();
                applyOnParentBridge |= bridgeVars.isEmpty();

                if (!applyOnParentBridge) {
                    // Determine if the current bridge has variables in common
                    // to
                    // the predicate to be solved
                    boolean varInCommon = false;
                    for (int j = 0; j < predI.arity(); j++) {
                        if (bridgeVars.contains(predI.var(j))) {
                            varInCommon = true;
                            break;
                        }
                    }
                    logger.info("Var(s) in common: {}", varInCommon);
                    applyOnParentBridge |= varInCommon;
                }
            }
            if (applyOnParentBridge) {
                predI.apply(bridge);
                logger.info("After normal apply of {}: {}", predI, bridge);
            } else {
                // No elements in common, we'll use a temporary solution and
                // generate the cartesian product
                final BridgeImpl bridgeI = SolverImpl.allocBridge(predI);
                logger.info("Before solving {} on local bridge: {}", predI, bridge);
                predI.apply(new LoggingBridge(bridgeI));
                logger.info("After solving  {} on local bridge: {}", predI, bridgeI);
                bridge.cartesianAnd(bridgeI);
                logger.info("After cartesian : {}", bridge);
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

    /**
     * @return Assume same provider as our first term, but normally should check all. 
     */
    @Override
    public Provider getProvider() {
        return ((Predicate) this.terms[0]).getProvider();
    }
    
}
