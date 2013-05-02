package org.logic2j.predsolver.gd3;

import static org.logic2j.predsolver.gd3.Gd3Provider.*;
import static org.logic2j.predsolver.impl.LogicProvider.*;

import org.junit.Ignore;
import org.junit.Test;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.solve.SolverImpl;

public class LexicalUseCaseTest {

    @Ignore("lexical sample only ")
    @Test
    public void mixing_context_db_logic() throws Exception {
        Var<Integer> X = new Var<Integer>();
        Var<Integer> C = new Var<Integer>();
        Var<String> U = new Var<String>();
        new SolverImpl().solve(mbua(X).and(owner(C, X)).and(url(C, U)).and(validUrl(U)), C);
    }

}
