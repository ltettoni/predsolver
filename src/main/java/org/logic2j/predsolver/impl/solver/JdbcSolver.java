package org.logic2j.predsolver.impl.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.DBPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Solver;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.api.tuple.Tuple3;
import org.logic2j.predsolver.impl.JdbcProvider;
import org.logic2j.predsolver.predicate.And;
import org.logic2j.predsolver.util.CollectionMap;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlBuilder3.ColumnOperatorBindingCriterion;
import org.logic2j.predsolver.util.SqlBuilder3.Criterion;

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
        CollectionMap<Var<?>, Predicate> mapping = new CollectionMap<Var<?>, Predicate>();
        fillVarPredicateMapping(pred, mapping);
        
        final SqlBuilder3 builder = new SqlBuilder3();
        fillSqlBuilder(pred, builder, mapping, projectionBinding);

        // Execute SQL
        JdbcProvider provider = (JdbcProvider) pred.getProvider();
        List<Object[]> resultSet = provider.execute(builder);
        // Now convert to tuple1
        return Collections.emptyList();

    }

    private void fillVarPredicateMapping(Predicate pred, CollectionMap<Var<?>, Predicate> mapping) {
        for (Term t: pred.terms) {
            if (t instanceof Predicate) {
                fillVarPredicateMapping((Predicate)t, mapping);
            } else if (t instanceof Var) {
                mapping.add((Var)t, pred);
            } else if (t instanceof Binding) {
                mapping.add(((Binding)t).getVar(), pred);
            }
        }
    }

    private void fillSqlBuilder(Predicate pred, SqlBuilder3 builder, CollectionMap<Var<?>, Predicate> mapping, Binding<?>... projections) {
        if (pred instanceof And) {
            And and = (And) pred;
            List<ColumnOperatorBindingCriterion> all = new ArrayList<SqlBuilder3.ColumnOperatorBindingCriterion>();
            for (Term elem : and.terms) {
                DBPredicate dbpred = (DBPredicate) elem;
                Criterion[] criteria = dbpred.getCriteria(builder);
                if (criteria==null) {
                    throw new IllegalStateException("DBPredicate " + dbpred + ".getCriteria() returns null");
                }
                for (Criterion criterion : criteria) {
                    ColumnOperatorBindingCriterion bindingCrit = (ColumnOperatorBindingCriterion) criterion;
                    bindingCrit = bindingCrit.effective(projections);
                    all.add(bindingCrit);
                }
            }
            logger.info("All binding criteria: {}", all);
            CollectionMap<Binding<?>, ColumnOperatorBindingCriterion> collMap = new CollectionMap<Binding<?>, SqlBuilder3.ColumnOperatorBindingCriterion>();
            for (ColumnOperatorBindingCriterion columnOperatorBindingCriterion : all) {
                collMap.add(columnOperatorBindingCriterion.getBinding(), columnOperatorBindingCriterion);
            }
            logger.info("collMap: {}", collMap);
            for (Entry<Binding<?>, Collection<ColumnOperatorBindingCriterion>> entries : collMap.entrySet()) {
                Collection<ColumnOperatorBindingCriterion> coll = entries.getValue();
                Iterator<ColumnOperatorBindingCriterion> iter = coll.iterator();
                if (iter.hasNext()) {
                    ColumnOperatorBindingCriterion first = iter.next();
                    if (first.getBinding().isBound()) {
                        builder.addConjunction(first);
                    }
                    // Add joins
                    while (iter.hasNext()) {
                        ColumnOperatorBindingCriterion next = iter.next();
                        builder.innerJoin(first.getColumn(), next.getColumn());
                    }
                }
            }
        } else if (pred instanceof DBPredicate) {
            Criterion[] criteria = ((DBPredicate) pred).getCriteria(builder);
            for (Criterion criterion : criteria) {
                if (criterion instanceof ColumnOperatorBindingCriterion) {
                    ColumnOperatorBindingCriterion bindingCrit = (ColumnOperatorBindingCriterion) criterion;
                    bindingCrit = bindingCrit.effective(projections);
                    if (bindingCrit.getBinding().isBound()) {
                        builder.addConjunction(bindingCrit);
                    }
                }
            }
        } else {
            throw new UnsupportedOperationException("Can only execute in DB");
        }
        logger.info("filled SqlBuilder: {}", builder.describe());
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
