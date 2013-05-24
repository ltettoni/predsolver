package org.logic2j.predsolver.gd3;

import static org.logic2j.predsolver.api.Binding.*;

import org.logic2j.predsolver.api.Binding;
import org.logic2j.predsolver.api.NonImplementedPredicate;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Term;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.gd3.domain.pred.CommId;
import org.logic2j.predsolver.gd3.domain.pred.CommNumbers;
import org.logic2j.predsolver.gd3.domain.pred.Owner;
import org.logic2j.predsolver.impl.JdbcProvider;

/**
 * Predicates defined in a particular case of relational data store, for example
 * the GD3 data model. Basically it's just about creating a type-strong DSL. One
 * day it could be inferred from an ER model.
 * 
 * @author Laurent
 */
public class Gd3Provider extends JdbcProvider {
    
    public Gd3Provider(String connectionString, String username, String password) {
        super(connectionString, username, password);
        setDataSource(derbyNetworkDataSource());
    }

    public static final Gd3Provider INSTANCE = new Gd3Provider("C:/Data/derby_dev_ci_gd30", "APP", "APP");

    public static Predicate mbua(Var<? extends Number> org) {
        return new NonImplementedPredicate(INSTANCE, "mbua", org);
    }

    public static Predicate active(Var<? extends Number> theId) {
        return new NonImplementedPredicate(INSTANCE, "active", theId);
    }

    public static Predicate ref(Var<? extends Number> theId, Var<? extends CharSequence> r) {
        return new NonImplementedPredicate(INSTANCE, "ref", theId, r);
    }

    public static Predicate url(Var<? extends Number> theArtefact, Var<? extends CharSequence> theAddress) {
        return new NonImplementedPredicate(INSTANCE, "url", theArtefact, theAddress);
    }

    public static Predicate classif(Var<? extends Number> theArtefact, Binding<String> cst) {
        return new NonImplementedPredicate(INSTANCE, "classification", theArtefact, cst);
    }

    // Syntactic sugar for constant args
    public static Predicate classif(Var<? extends Number> theArtefact, String... str) {
        return classif(theArtefact, cst(str));
    }

    public Predicate owner(Var<Integer> theOwned, Var<Integer> theOwner) {
        return new Owner(this, theOwned, theOwner.free());
    }

    public Predicate owner(Var<Integer> theOwned, Binding<Integer> theOwner) {
        return new Owner(this, theOwned, theOwner);
    }

    public Predicate committee(Var<Integer> theId) {
        return new CommId(this, theId);
    }

    public Predicate commNumbers(Var<Integer> theId, Term... theArgs) {
        return new CommNumbers(this, theArgs);
    }

    public Predicate comIso(Var<Integer> comm) {
        return committee(comm).and(owner(comm, cst(68)));
    }

}
