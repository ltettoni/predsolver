package org.logic2j.predsolver.sample;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;

public class Fail extends Predicate {

    public Fail() {
        super(LogicProvider.INSTANCE, "true");
    }

    @Override
    public void apply(Bridge bridge) {
        // Do not push any solution
    }

}
