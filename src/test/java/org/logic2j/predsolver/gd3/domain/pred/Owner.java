package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlBuilder3.Criterion;
import org.logic2j.predsolver.util.SqlBuilder3.Operator;

public class Owner extends Predicate implements DBPredicate {

    private final ColumnInfo[] columnInfos;

    public Owner(Provider theProvider, Var<Integer> theOwned, Binding<Integer> theOwner) {
        super(theProvider, "owner", theOwned, theOwner);
        this.columnInfos = new ColumnInfo[] { //
        new ColumnInfo("pred_owner", "id", binding(0)), //
                new ColumnInfo("pred_owner", "owner", binding(1)), //
        };
    }

    @Override
    public Criterion[] getCriteria(SqlBuilder3 sb) {
        return new Criterion[] { 
                sb.criterion(sb.column(sb.table("pred_owner"), "id"), Operator.EQ, binding(0)),
                sb.criterion(sb.column(sb.table("pred_owner"), "owner"), Operator.EQ, binding(1)),
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
