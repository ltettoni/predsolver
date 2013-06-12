package org.logic2j.predsolver.gd3.domain.dto.core;

import java.util.Date;

public abstract class Artefact {

    public static interface Predicate1 {
        public boolean filter(Artefact that);
    }

    public static interface Predicate2<T0> {
        public T0[] forward(Artefact that);
        public Artefact[] reverse(T0 source);
    }


    public enum State {
        ENT_CREATED, ENT_ACTIVE,
    }

    private Long id;
    private String display; // The most basic String property
    private String sortKey;
    private String classification;
    private Date mandateEnd;

    private State status = State.ENT_ACTIVE; // An enum property

    private Artefact owner;

    public Artefact() {
        super();
    }

    public Artefact(Long theId) {
        super();
        this.id = theId;
    }

    // ---------------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------------

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String theName) {
        this.display = theName;
    }

    public String getSortKey() {
        return this.sortKey;
    }

    public void setSortKey(String theSortKey) {
        this.sortKey = theSortKey;
    }

    public State getStatus() {
        return this.status;
    }

    public void setStatus(State theState) {
        this.status = theState;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long theId) {
        this.id = theId;
    }

    public Artefact getOwner() {
        return this.owner;
    }

    public void setOwner(Artefact theOwner) {
        this.owner = theOwner;
    }

    public Date getMandateEnd() {
        return this.mandateEnd;
    }

    public void setMandateEnd(Date theMandateEnd) {
        this.mandateEnd = theMandateEnd;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String theCategory) {
        this.classification = theCategory;
    }

    // ---------------------------------------------------------------------------
    // Core
    // ---------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getDisplay() == null) ? 0 : this.getDisplay().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Committee other = (Committee) obj;
        if (this.getDisplay() == null) {
            if (other.getDisplay() != null) {
                return false;
            }
        } else if (!this.getDisplay().equals(other.getDisplay())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '(' + getDisplay() + ')';
    }
}
