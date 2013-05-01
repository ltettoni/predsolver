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

/**
 * Sample domain model: a Committee pseudo-entity to test the security API.
 *
 * @version $Id: MiniPers.java,v 1.1 2011-04-01 12:36:15 tettoni Exp $
 */
public class Person extends Artefact {

    public static interface Predicate1 {
        public boolean apply(Person theArgument);
    }

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String theEmail) {
        this.email = theEmail;
    }

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
        Person other = (Person) obj;
        if (this.getDisplay() == null) {
            if (other.getDisplay() != null) {
                return false;
            }
        } else if (!this.getDisplay().equals(other.getDisplay())) {
            return false;
        }
        return true;
    }

}
