package org.logic2j.predsolver.impl.solver.bridge;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.api.tuple.Tuple1;
import org.logic2j.predsolver.api.tuple.Tuple2;
import org.logic2j.predsolver.api.tuple.Tuple3;

public class LoggingBridge implements Bridge {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LoggingBridge.class);

    private final Bridge wrapped;

    public LoggingBridge(Bridge wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public boolean isBound(Var<?> theVar) {
        logger.info("B => isBound({})", theVar);
        final boolean result = wrapped.isBound(theVar);
        logger.info("B <= {}", result);
        return result;
    }

    @Override
    public List<Record> fetch(Var<?>... vars) {
        if (vars.length == 0) {
            logger.info("B => fetch()");
        } else if (vars.length == 1) {
            logger.info("B => fetch({})", vars[0]);
        } else {
            logger.info("B => fetch({})", Arrays.asList(vars));
        }
        final List<Record> result = wrapped.fetch(vars);
        logger.info("B <= {}", result);
        return result;
    }

    @Override
    public Record add(Record rec) {
        logger.info("B => add({})", rec);
        final Record result = wrapped.add(rec);
        logger.info("B <= {}", result);
        return result;
    }

    @Override
    public <T> T get(Record toRead, Var<T> theVar) {
        logger.info("B => get({}, {})", toRead, theVar);
        final T result = wrapped.get(toRead, theVar);
        logger.info("B <= {}", result);
        return result;
    }

    @Override
    public <T> Record set(Record toWrite, Var<T> theVar, T theValue) {
        logger.info("B => set({}, {}, " + theValue + ")", toWrite, theVar);
        wrapped.set(toWrite, theVar, theValue);
        logger.info("B <= {}", toWrite);
        return toWrite;
    }

    @Override
    public void remove(Record toRemove) {
        logger.info("B => remove({})", toRemove);
        wrapped.remove(toRemove);
    }

    @Override
    public String toString() {
        return "Logging " + this.wrapped;
    }

    @Override
    public void parallelColumn(Binding<?> extraBinding) {
        logger.info("B => parallelColumn({})", extraBinding);
        wrapped.parallelColumn(extraBinding);
    }

    @Override
    public void cartesianColumn(Binding<?> extraBinding) {
        logger.info("B => cartesianColumn({})", extraBinding);
        wrapped.cartesianColumn(extraBinding);
    }

    @Override
    public void cartesianAnd(BridgeImpl that) {
        logger.info("B => cartesian({})", that);
        wrapped.cartesianAnd(that);
        logger.info("B  = {}", this);
    }

    @Override
    public Set<Var<?>> vars() {
        logger.info("B => vars()");
        final Set<Var<?>> result = wrapped.vars();
        logger.info("B <= {}", result);
        return result;
    }

    @Override
    public <T> List<Tuple1<T>> asList(Var<T> theVar) {
        return this.wrapped.asList(theVar);
    }

    @Override
    public <T0, T1> List<Tuple2<T0, T1>> asList(Var<T0> var0, Var<T1> var1) {
        return this.wrapped.asList(var0, var1);
    }

    @Override
    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> asList(Var<T0> var0, Var<T1> var1, Var<T2> var2) {
        return this.wrapped.asList(var0, var1, var2);
    }

}
