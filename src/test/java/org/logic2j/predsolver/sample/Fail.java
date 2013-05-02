package org.logic2j.predsolver.sample;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.impl.solver.bridge.Record;

public class Fail extends Predicate {

    public Fail() {
        super(LogicProvider.INSTANCE, "true");
    }

    @Override
    public void apply(Bridge bridge) {
//        // Remove all solutions
//        for (Record rec : bridge.fetch()) {
//            bridge.remove(rec);
//        }
    }

}
