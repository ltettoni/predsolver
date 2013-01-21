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

import org.logic2j.predsolver.model.Var;

/**
 * Sample domain model: a pseudo-entity to test the security API. Also making
 * sure it works with hierarchies of classes. Notice I make this "Proposable"
 * and not its parent class, on purpose.
 * 
 * @version $Id: Committee.java,v 1.6 2011-06-29 22:55:08 tettoni Exp $
 */
public class Committee extends Artefact {

	public static interface Predicate1 {
		public boolean apply(Committee that, Var<Committee> theArgument);
	}
	
	public static interface Predicate2<T0> {
		public boolean apply(Committee that, Var<Committee> theArgument, Var<T0> theVar);
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
