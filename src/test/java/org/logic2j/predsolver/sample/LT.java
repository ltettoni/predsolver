package org.logic2j.predsolver.sample;

import java.util.Iterator;

import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.impl.LogicProvider;
import org.logic2j.predsolver.predicate.Predicate1;

public class LT extends Predicate1<Integer> {

    private final int constant;
    
    public LT(Term x, int constant) {
        super(LogicProvider.INSTANCE, "lt", x);
        this.constant = constant;
    }

    @Override
    protected boolean membershipFunction(Integer value) {
        return value < constant;
    }

    @Override
    protected Iterable<Integer> generatorFunction() {
        final Iterator<Integer> iter = new Iterator<Integer>() {
            int value = constant - 1;
            @Override
            public boolean hasNext() {
                return value>=0;
            }

            @Override
            public Integer next() {
                return value--;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove on this iterator: " + this);
                
            }
        };
        
        return new Iterable<Integer>() {
            
            @Override
            public Iterator<Integer> iterator() {
                return iter;
            }
        };
    }

    

}
