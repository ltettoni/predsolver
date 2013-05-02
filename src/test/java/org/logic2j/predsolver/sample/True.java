package org.logic2j.predsolver.sample;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.impl.solver.bridge.Record;

public class True extends Predicate {

    public True() {
        super(LogicProvider.INSTANCE, "true");
    }

    @Override
    public void apply(Bridge bridge) {
        // One single solution - without data bound
        bridge.add(new Record());
    }

}
