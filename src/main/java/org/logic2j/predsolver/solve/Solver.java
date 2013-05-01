package org.logic2j.predsolver.solve;

import java.util.List;
import java.util.Set;

import org.logic2j.predsolver.model.Binding;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.bridge.BridgeImpl;
import org.logic2j.predsolver.solve.bridge.LoggingBridge;
import org.logic2j.predsolver.tuple.Tuple1;
import org.logic2j.predsolver.tuple.Tuple2;
import org.logic2j.predsolver.tuple.Tuple3;

/**
 * Solving goals happens in this class.
 * 
 * @author Laurent
 */
public class Solver {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Solver.class);

    // -----------------
    // One argument
    // -----------------

    public <T0> List<Tuple1<T0>> solve(Predicate pred, Var<T0> v0) {
        return solve(pred, v0.freeBinding());
    }

    public <T0> List<Tuple1<T0>> solve(Predicate pred, final Binding<T0> projectionBinding) {
        // Solving a normal predicate (not a boolean operator)
        final BridgeImpl bridge = allocBridge(pred, projectionBinding);
        logger.info("Before solving: {}", bridge);
        pred.apply(new LoggingBridge(bridge));
        logger.info("After solving : {}", bridge);
        return bridge.asList(projectionBinding.var);
    }

    // -----------------
    // Two arguments
    // -----------------

    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Var<T1> v1) {
        return solve(pred, v0.freeBinding(), v1.freeBinding());
    }

    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Var<T0> v0, Binding<T1> v1) {
        return solve(pred, v0.freeBinding(), v1);
    }

    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Var<T1> v1) {
        return solve(pred, v0, v1.freeBinding());
    }

    public <T0, T1> List<Tuple2<T0, T1>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1) {
        final BridgeImpl bridge = allocBridge(pred, new Binding<?>[] { v0, v1 });
        logger.info("Before solving: {}", bridge);
        pred.apply(new LoggingBridge(bridge));
        logger.info("After solving : {}", bridge);
        return bridge.asList(v0.var, v1.var);
    }


    // -----------------
    // Three arguments
    // -----------------

    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Var<T0> v0, Var<T1> v1, Var<T2> v2) {
        return solve(pred, v0.freeBinding(), v1.freeBinding(), v2.freeBinding());
    }

    public <T0, T1, T2> List<Tuple3<T0, T1, T2>> solve(Predicate pred, Binding<T0> v0, Binding<T1> v1, Binding<T2> v2) {
        final BridgeImpl bridge = allocBridge(pred, new Binding<?>[] { v0, v1, v2 });
        logger.info("Before solving: {}", bridge);
        pred.apply(new LoggingBridge(bridge));
        logger.info("After solving : {}", bridge);
        return bridge.asList(v0.var, v1.var, v2.var);
    }

    // -----------------
    // Support methods
    // -----------------

    public static BridgeImpl allocBridge(Predicate pred, final Binding<?>... projectionBindings) {
        final BridgeImpl bridge = new BridgeImpl();
        final Set<Var<?>> publicVars = pred.publicVars();
        for (Binding<?> binding : projectionBindings) {
            if (publicVars.contains(binding.var)) {
                bridge.parallelColumn(binding);
            }
        }
        return bridge;
    }

    // --------------------------------------------------------------------------------
    // Parking
    // --------------------------------------------------------------------------------

    // private static <T> List<T> linkedList(T... values) {
    // final List<T> result = new LinkedList<T>();
    // for (T value : values) {
    // result.add(value);
    // }
    // return result;
    // }

}
