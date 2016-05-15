package org.logic2j.predsolver.impl;

import org.logic2j.predsolver.api.NonImplementedPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.predicate.And;
import org.logic2j.predsolver.predicate.Member;
import org.logic2j.predsolver.predicate.Or;
import org.logic2j.predsolver.predicate.*;

/**
 * Define base predicates that do not depend on a particular data source. Note
 * that some of them need also be defined in a data source, for example length()
 * which is supported by SQL.
 * 
 * @author Laurent
 */
public class LogicProvider implements Provider {
    public static final LogicProvider INSTANCE = new LogicProvider();

    public static <T> Square sqr(Var<T> x, Var<T> y) {
        return new Square(x, y);
    }
    
    public static <T> Abs abs(Var<T> x, Var<T> y) {
        return new Abs(x, y);
    }
    
    public static <T> Digit digit(Var<T> x) {
        return new Digit(x);
    }
    
    
    public static Member<Integer> member(Var<Integer> x, Integer... values) {
        return new Member<Integer>(x, values);
    }
    
    
    public static Range range(Var<Integer> x, int min, int max) {
        return new Range(x, min, max);
    }
    
    public static <T> Even08 even(Var<T> x) {
        return new Even08(x);
    }
    
    public static <T> Odd19 odd(Var<T> x) {
        return new Odd19(x);
    }
    
    public static And and(Predicate... preds) {
        return new And(preds);
    }
    
    public static Or or(Predicate... preds) {
        return new Or(preds);
    }

    public static Predicate not(Predicate pred) {
        return new NonImplementedPredicate(INSTANCE, "not", pred);
    }

    public static Predicate exists(Predicate pred) {
        return new NonImplementedPredicate(INSTANCE, "exists", pred);
    }
    
    /**
     * True if x is an integer
     * 
     * @param x
     * @return
     */
    public static <T> Predicate integer(Var<T> x) {
        return new NonImplementedPredicate(INSTANCE, "integer", x);
    }

    /**
     * Equivalence between a variable value(s) and string representation(s)
     * 
     * @param x
     * @param s
     * @return
     */
    public static <T> Predicate string(Var<T> x, Var<String> s) {
        return new NonImplementedPredicate(INSTANCE, "string", x, s);
    }

    public static <T> Predicate validUrl(Var<? extends CharSequence> s) {
        return new NonImplementedPredicate(INSTANCE, "validUrl", s);
    }


}
