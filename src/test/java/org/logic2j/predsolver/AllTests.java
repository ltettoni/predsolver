package org.logic2j.predsolver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.logic2j.predsolver.gd3.Gd3ProviderUseCaseTest;
import org.logic2j.predsolver.gd3.LexicalUseCaseTest;
import org.logic2j.predsolver.model.BindingTest;
import org.logic2j.predsolver.model.VarTest;
import org.logic2j.predsolver.pred.AbsTest;
import org.logic2j.predsolver.pred.AndTest;
import org.logic2j.predsolver.pred.DigitTest;
import org.logic2j.predsolver.pred.Even08Test;
import org.logic2j.predsolver.pred.MemberTest;
import org.logic2j.predsolver.pred.OrTest;
import org.logic2j.predsolver.pred.RangeTest;
import org.logic2j.predsolver.pred.SquareTest;
import org.logic2j.predsolver.solve.bridge.BridgeImplTest;

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
