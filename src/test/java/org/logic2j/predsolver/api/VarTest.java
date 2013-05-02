package org.logic2j.predsolver.api;

import org.junit.Before;
import org.junit.Test;
import org.logic2j.predsolver.api.Var;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Laurent
 */
public class VarTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VarTest.class);

    private Var<Integer> vInt;

    @Before
    public void setUp() {
        this.vInt = new Var<Integer>();
    }

    @Test
    public void publicVariable() {
        vInt = new Var<Integer>("X");
        logger.info("Variable: {}", vInt);
        assertTrue(vInt.isPublic());
    }

    @Test
    public void privateVariable() {
        vInt = new Var<Integer>();
        logger.info("Variable: {}", vInt);
        assertFalse(vInt.isPublic());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void privateVariableNotAllowed() {
        vInt = new Var<Integer>("_X");
    }

}
