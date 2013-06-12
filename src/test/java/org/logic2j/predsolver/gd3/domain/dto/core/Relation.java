package org.logic2j.predsolver.gd3.domain.dto.core;

import java.util.Date;

public class Relation {

    private Long id;

    private Artefact from;
    private Artefact to;
    private String type;
    private String subtype;
    private Date startDate;
    private Date endDate;

    /**
     * @param theType
     */
    public Relation(String theType) {
        this.type = theType;
    }

    //---------------------------------------------------------------------------
    // Accessors
    //---------------------------------------------------------------------------

    public Long getId() {
        return this.id;
    }

    public void setId(Long theId) {
        this.id = theId;
    }

    public Artefact getFrom() {
        return this.from;
    }

    public void setFrom(Artefact theFrom) {
        this.from = theFrom;
    }

    public Artefact getTo() {
        return this.to;
    }

    public void setTo(Artefact theTo) {
        this.to = theTo;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date theStartDate) {
        this.startDate = theStartDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date theEndDate) {
        this.endDate = theEndDate;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String theType) {
        this.type = theType;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String theSubtype) {
        this.subtype = theSubtype;
    }

}
