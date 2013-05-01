/*
 * Copyright (c) ISO (International Organization for Standardization).
 * 
 * THIS SOFTWARE IS PROVIDED BY ISOurce (ISO Source FOR SOFTWARE DEVELOPMENT AND
 * COLLABORATION) AMONG ISO/CS AND ISO NATIONAL MEMBER BODIES, "AS IS" AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL ISOurce OR ITS CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * For more information on the ISOurce project, please see
 * http://isource.iso.org/
 */
package org.logic2j.predsolver.gd3.domain.dto.core;

import java.util.Date;

/**
 * Mimics a relation in memory, eg a Pojo where validation occurs before persisting.
 *
 * @version $Id: Relation.java,v 1.1 2011-07-26 22:30:21 tettoni Exp $
 */
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
