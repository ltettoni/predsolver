package org.logic2j.predsolver.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.logic2j.predsolver.model.Binding.cst;

/**
 * TODO: link two variables together. One solution (compatible with predicate
 * calculus) is equal(A, B), but from an API perspective, A.bind(B) would be
 * simpler.
 *
 * @author Laurent
 */
public class BindingTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BindingTest.class);

    private Binding<Integer> vInt;

    @Before
    public void setUp() {
        this.vInt = new Binding<Integer>(new Var<Integer>("Int"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void scalarConstant() {
        vInt = cst(123);
    }

    @Test
    public void vectorialConstant() {
        vInt = cst(2, 3, 4);
        logger.info("Constant Vectorial Var: {}", vInt);
        assertTrue(vInt.isBound());
        assertEquals(3, vInt.getValues().size());
    }
}
