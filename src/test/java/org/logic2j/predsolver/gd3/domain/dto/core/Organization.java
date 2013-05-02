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

import org.logic2j.predsolver.api.Var;

/**
 * Sample domain model: an Organization.
 *
 * @version $Id: Organization.java,v 1.1 2011-04-06 07:52:48 tettoni Exp $
 */
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
