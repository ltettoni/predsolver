package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;

public class CommId extends Predicate implements DBPredicate {

    private final ColumnInfo[] columnInfos;

    public CommId(Provider theProvider, Var<Long> theId) {
        super(theProvider, "committee", theId);
        this.columnInfos = new ColumnInfo[] { //
        // new ColumnInfo("committee", "id", binding(0)), //
                 new ColumnInfo("committee", "tc_number", binding(0)), //
                 new ColumnInfo("committee", "sc_number", Binding.cst(1,2)), //
        };
    }

    @Override
    public ColumnInfo[] getColumnSpec() {
        return this.columnInfos;
    }

    @Override
    public void apply(Bridge bridge) {
        throw new UnsupportedOperationException("Cannot solve " + this + " off context of DB or JavaBeans");
    }

}
