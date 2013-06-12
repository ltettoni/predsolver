package org.logic2j.predsolver.gd3.domain.dto.core;

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
