package org.logic2j.predsolver.impl.solver;

import java.util.Arrays;
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
import org.logic2j.predsolver.util.SqlBuilder3;

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
        if (pred instanceof DBPredicate) {
            ColumnInfo[] columnSpec = ((DBPredicate) pred).getColumnSpec();
            // Generate SQL
            
            // Execute SQL
            SqlBuilder3 builder = new SqlBuilder3();
            builder.table("zip_code");
            builder.addProjection(builder.column(builder.table("zip_code"), "zip_code"));
            builder.setDistinct(true);
            JdbcProvider provider = (JdbcProvider)pred.getProvider();
            provider.execute(builder);
            logger.info("DB predicate for {}", Arrays.asList(columnSpec));
        }
        return null;
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
