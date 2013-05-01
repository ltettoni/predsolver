package org.logic2j.predsolver.gd3;

import org.junit.Ignore;
import org.junit.Test;
import org.logic2j.predsolver.model.Var;
import org.logic2j.predsolver.solve.Solver;

import static org.logic2j.predsolver.gd3.Gd3Provider.owner;
import static org.logic2j.predsolver.gd3.Gd3Provider.url;
import static org.logic2j.predsolver.impl.ContextProvider.mbua;
import static org.logic2j.predsolver.impl.LogicProvider.validUrl;

public class LexicalUseCaseTest {

    @Ignore("lexical sample only ")
    @Test
    public void mixing_context_db_logic() throws Exception {
        Var<Integer> X = new Var<Integer>();
        Var<Integer> C = new Var<Integer>();
        Var<String> U = new Var<String>();
        new Solver().solve(mbua(X).and(owner(C, X)).and(url(C, U)).and(validUrl(U)), C);
    }

}
