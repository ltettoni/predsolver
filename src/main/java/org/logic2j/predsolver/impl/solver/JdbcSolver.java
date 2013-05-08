package org.logic2j.predsolver.impl.solver;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.DBPredicate.ColumnInfo;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Solver;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.api.tuple.Tuple3;
import org.logic2j.predsolver.impl.JdbcProvider;
import org.logic2j.predsolver.predicate.And;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlBuilder3.Column;
import org.logic2j.predsolver.util.SqlBuilder3.ColumnOperatorParameterCriterion;
import org.logic2j.predsolver.util.SqlBuilder3.Operator;
import org.logic2j.predsolver.util.SqlBuilder3.Table;

public class JdbcSolver implements Solver {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JdbcSolver.class);

    public JdbcSolver(DataSource dataSource) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public <T0> List<Tuple1<T0>> solve(Predicate pred, Var<T0> v0) {
        return solve(pred, v0.free());
    }

    @Override
    public <T0> List<Tuple1<T0>> solve(Predicate pred, Binding<T0> projectionBinding) {
        final SqlBuilder3 builder = new SqlBuilder3();
        fillSqlBuilder(pred, builder, projectionBinding);

        // Execute SQL
        JdbcProvider provider = (JdbcProvider) pred.getProvider();
        List<Object[]> resultSet = provider.execute(builder);
        // Now convert to tuple1
        return Collections.emptyList();

    }

    private void fillSqlBuilder(Predicate pred, SqlBuilder3 builder, Binding<?>... projections) {
        if (pred instanceof And) {

        } else if (pred instanceof DBPredicate) {
            ColumnInfo[] columnSpec = ((DBPredicate) pred).getColumnSpec();
            // Generate SQL

            for (ColumnInfo columnInfo : columnSpec) {
                Table table = builder.table(columnInfo.table);
                Column column = builder.column(table, columnInfo.column);
                Binding<?> eb = effectiveBinding(columnInfo.value, pred, projections[0]);
                if (eb.isBound()) {
                    Operator op = Operator.valueOfSql(columnInfo.operator);
                    ColumnOperatorParameterCriterion criterion = builder.criterion(column, op, eb.getValues());
                    builder.addConjunction(criterion);
                }
            }
        } else {
            throw new UnsupportedOperationException("Can only execute in DB");
        }
    }

    private <T0> Binding<T0> effectiveBinding(Binding<?> specBinding, Predicate pred, Binding<T0> projBinding) {
        if (specBinding.isBound()) {
            return (Binding<T0>) specBinding;
        }
        return projBinding;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Var<T1> v1) {

        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Binding<T1> v1) {

        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Var<T1> v1) {

        return null;
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1) {

        return null;
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2) {

        return null;
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1, Binding<T2> v2) {

        return null;
    }

}
