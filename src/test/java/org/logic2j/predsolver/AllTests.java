package org.logic2j.predsolver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.logic2j.predsolver.api.BindingTest;
import org.logic2j.predsolver.api.VarTest;
import org.logic2j.predsolver.gd3.Gd3ProviderUseCaseTest;
import org.logic2j.predsolver.gd3.LexicalUseCaseTest;
import org.logic2j.predsolver.impl.solver.SolverTest;
import org.logic2j.predsolver.impl.solver.bridge.BridgeImplTest;
import org.logic2j.predsolver.predicate.AbsTest;
import org.logic2j.predsolver.predicate.AndTest;
import org.logic2j.predsolver.predicate.DigitTest;
import org.logic2j.predsolver.predicate.Even08Test;
import org.logic2j.predsolver.predicate.MemberTest;
import org.logic2j.predsolver.predicate.OrTest;
import org.logic2j.predsolver.predicate.RangeTest;
import org.logic2j.predsolver.predicate.SquareTest;

@RunWith(Suite.class)
@SuiteClasses({ //
VarTest.class, //
        BindingTest.class, //
        BridgeImplTest.class, //
        Even08Test.class, //
        MemberTest.class, //
        DigitTest.class, //
        RangeTest.class, //
        AbsTest.class, //
        SquareTest.class, //
        AndTest.class, //
        OrTest.class, //
        SolverTest.class, //
        Gd3ProviderUseCaseTest.class, //
        LexicalUseCaseTest.class, //
})
public class AllTests {

}
