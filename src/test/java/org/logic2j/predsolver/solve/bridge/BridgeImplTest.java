package org.logic2j.predsolver.solve.bridge;

import static org.junit.Assert.*;

import org.junit.Test;
import org.logic2j.predsolver.model.Binding;
import org.logic2j.predsolver.pred.PredTestBase;

public class BridgeImplTest extends PredTestBase {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BridgeImplTest.class);
  

  @Test
  public void empty() {
      Bridge bridge = new BridgeImpl();
      assertEquals(0, bridge.asList(null).size());
  }
  
  
    @Test
    public void cartesianColumn() {
        Bridge bridge = new LoggingBridge(new BridgeImpl());
        assertEquals(0, bridge.asList(null).size());
        //
        bridge.cartesianColumn(Binding.cst(1,2,3,4));
        logger.info("After cartesian1: {}", bridge);
        assertEquals(4, bridge.asList(null).size());
        //
        bridge.cartesianColumn(Binding.cst(7,8,9));
        logger.info("After cartesian2: {}", bridge);
        assertEquals(12, bridge.asList(null).size());
    }

    @Test
    public void cartesianFromEmpty() {
        Bridge original = new LoggingBridge(new BridgeImpl());
        BridgeImpl bridge2 = new BridgeImpl();
        bridge2.add(new Record().set(X, 1).set(Y, 11));
        bridge2.add(new Record().set(X, 2).set(Y, 12));
        bridge2.add(new Record().set(X, 3).set(Y, 13));
        original.cartesianAnd(bridge2);
        assertEquals(3, original.asList(null).size());
    }
    
    
    @Test
    public void cartesianWithEmpty() {
        Bridge original = new LoggingBridge(new BridgeImpl());
        original.add(new Record().set(X, 1).set(Y, 11));
        original.add(new Record().set(X, 2).set(Y, 11));
        original.add(new Record().set(X, 3).set(Y, 11));
        //
        BridgeImpl merging = new BridgeImpl();
        original.cartesianAnd(merging);
        logger.info("After cartesian: {}", original);
        assertEquals(0, original.asList(null).size());
    }
    
    
    @Test
    public void cartesianDifferentVarsSingle() {
        Bridge original = new LoggingBridge(new BridgeImpl());
        original.add(new Record().set(X, 1).set(Y, 11));
        original.add(new Record().set(X, 2).set(Y, 11));
        original.add(new Record().set(X, 3).set(Y, 11));
        //
        BridgeImpl merging = new BridgeImpl();
        merging.add(new Record().set(T, 101).set(U, 111));
        original.cartesianAnd(merging);
        logger.info("After cartesian: {}", original);
        assertEquals(3, original.asList(null).size());
    }
    
    
    @Test
    public void cartesianDifferentVarsDouble() {
        Bridge original = new LoggingBridge(new BridgeImpl());
        original.add(new Record().set(X, 1).set(Y, 11));
        original.add(new Record().set(X, 2).set(Y, 11));
        original.add(new Record().set(X, 3).set(Y, 11));
        //
        BridgeImpl merging = new BridgeImpl();
        merging.add(new Record().set(T, 101).set(U, 111));
        merging.add(new Record().set(T, 102).set(U, 112));
        original.cartesianAnd(merging);
        logger.info("After cartesian: {}", original);
        assertEquals(6, original.asList(null).size());
    }
    
}
