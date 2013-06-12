package org.logic2j.predsolver.gd3.domain.pred;

import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.solver.bridge.Bridge;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlBuilder3.Criterion;
import org.logic2j.predsolver.util.SqlBuilder3.Operator;

public class CommId extends Predicate implements DBPredicate {

//    private final ColumnInfo[] columnInfos;

    public CommId(Provider theProvider, Term theId) {
        super(theProvider, "committee", theId);
//        this.columnInfos = new ColumnInfo[] { //
//        new ColumnInfo("committee", "id", binding(0)), //
//        };
    }

    @Override
    public Criterion[] getCriteria(SqlBuilder3 sb) {
        return new Criterion[] { 
                sb.criterion(sb.column(sb.table("committee"), "id"), Operator.EQ, binding(0))
        };
    }

//    @Override
//    public ColumnInfo[] getColumnSpec() {
//        return this.columnInfos;
//    }

    @Override
    public void apply(Bridge bridge) {
        throw new UnsupportedOperationException("Cannot solve " + this + " off context of DB or JavaBeans");
    }

}
