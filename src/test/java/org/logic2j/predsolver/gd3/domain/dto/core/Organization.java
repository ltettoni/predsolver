package org.logic2j.predsolver.gd3.domain.dto.core;

import org.logic2j.predsolver.api.Var;

public class Organization extends Artefact {

    public static interface Predicate1 {
        public boolean apply(Organization that, Var<Organization> theArgument);
    }

    public static interface Predicate2<T0> {
        public boolean apply(Organization that, Var<Organization> theArgument, Var<T0> theVar);
    }

    private String acronym;

    public Organization() {
        super();
    }

    public Organization(Long theId) {
        super(theId);
    }

    // ---------------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------------


    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    // ---------------------------------------------------------------------------
    // Factories
    // ---------------------------------------------------------------------------

    public static Organization createIso() {
        final Organization iso = new Organization(68L);
        iso.setDisplay("ISO");
        return iso;
    }


}
