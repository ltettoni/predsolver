package org.logic2j.predsolver.gd3.domain.dto.core;


public class Committee extends Artefact {

    public static interface Predicate2<T0> {
        public T0[] forward(Committee that);
        public Committee[] reverse(T0 source);
    }

    private Committee parent; // Hierarchical relation

    private String title;
    private String sector;
    private String reference;
    private String scope;
    private int tcNumber; // Number, with some constraint on hierarchy
    private int scNumber; // Number

    public Committee() {
        super();
    }

    public Committee(Long theId) {
        super(theId);
    }

    // ---------------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------------

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getTcNumber() {
        return this.tcNumber;
    }

    public void setTcNumber(int theLevel1) {
        this.tcNumber = theLevel1;
    }

    public int getScNumber() {
        return this.scNumber;
    }

    public void setScNumber(int theLevel2) {
        this.scNumber = theLevel2;
    }

    public Committee getParent() {
        return this.parent;
    }

    public void setParent(Committee theParent) {
        this.parent = theParent;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String theSector) {
        this.sector = theSector;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String theScope) {
        this.scope = theScope;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String theTitle) {
        this.title = theTitle;
    }

    // ---------------------------------------------------------------------------
    // Factories
    // ---------------------------------------------------------------------------

    public static Committee createCasco() {
        final Committee casco = new Committee(54998L);
        casco.setDisplay("ISO/CASCO");
        casco.setOwner(Organization.createIso());
        return casco;
    }

    public static Committee createOtherCommittee() {
        final Committee comm = new Committee(1234L);
        comm.setDisplay("ISO/Other");
        comm.setOwner(Organization.createIso());
        comm.setStatus(State.ENT_CREATED);
        return comm;
    }
}
