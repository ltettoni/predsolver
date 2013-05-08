package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlBuilder3.Criterion;
import org.logic2j.predsolver.util.SqlBuilder3.Operator;

public class CommNumbers extends Predicate implements DBPredicate {

    private final ColumnInfo[] columnInfos;

    public CommNumbers(Provider theProvider, Var<Integer> theId) {
        super(theProvider, "committee", theId);
        this.columnInfos = new ColumnInfo[] { new ColumnInfo("committee", "tc_number", binding(0)),
                new ColumnInfo("committee", "sc_number", binding(1)) };
    }

    @Override
    public Criterion[] getCriteria(SqlBuilder3 sb) {
        return new Criterion[] { 
                sb.new ColumnOperatorBindingCriterion(sb.column(sb.table("committee"), "tc_number"), Operator.EQ, binding(0)),
                sb.new ColumnOperatorBindingCriterion(sb.column(sb.table("committee"), "sc_number"), Operator.EQ, binding(1)),
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
