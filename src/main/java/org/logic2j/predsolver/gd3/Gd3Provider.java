package org.logic2j.predsolver.gd3;

import static org.logic2j.predsolver.model.Var.*;

import org.logic2j.predsolver.impl.JdbcProvider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;

/**
 * Predicates defined in a particular case of relational data store, 
 * for example the GD3 data model.
 * Basically it's just about creating a type-strong DSL.
 * One day it could be inferred from an ER model.
 * 
 * @author Laurent
 */
public class Gd3Provider extends JdbcProvider {
	private static final Gd3Provider INSTANCE = new Gd3Provider();
	
	public static Predicate committee(Var<? extends Number> theId) {
		return new Predicate(INSTANCE, "committee", theId);
	}
	
	public static Predicate active(Var<? extends Number> theId) {
		return new Predicate(INSTANCE, "active", theId);
	}

	public static Predicate ref(Var<? extends Number> theId, Var<? extends CharSequence> r) {
		return new Predicate(INSTANCE, "ref", theId, r);
	}

	public static Predicate owner(Var<? extends Number> theArtefact, Var<? extends Number> theOwner) {
		return new Predicate(INSTANCE, "owner", theArtefact, theOwner);
	}

	public static Predicate url(Var<? extends Number> theArtefact, Var<? extends CharSequence> theAddress) {
		return new Predicate(INSTANCE, "url", theArtefact, theAddress);
	}

	public static Predicate classif(Var<? extends Number> theArtefact, Var<String> cst) {
		return new Predicate(INSTANCE, "classification", theArtefact, cst);
	}
	
	// Syntactic sugar for constant args
	public static Predicate classif(Var<? extends Number> theArtefact, String... str) {
		return classif(theArtefact, cst(str));
	}

}
