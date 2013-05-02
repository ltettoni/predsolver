package org.logic2j.predsolver.gd3;

import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.impl.JdbcProvider;
import org.logic2j.predsolver.predicate.Member;

/**
 * Provide assertions from the J2SE session.
 * 
 * @author Laurent
 */
public class SessionProvider extends JdbcProvider {
    public static final SessionProvider INSTANCE = new SessionProvider();

    /**
     * Current authenticated user is member of the specified organization.
     * @param org
     * @return
     */
    public Predicate mbuaOf(Var<Integer> org) {
        return new Member<Integer>(INSTANCE, "mbuaOf", org, 1711);
    }

    /**
     * Current user is authenticated in the specified group.
     * @param groupName
     * @return
     */
    public Predicate authInGroup(final Var<String> groupName) {
        return new Member<String>(INSTANCE, "mbuaOf", groupName, "group-1", "group-2", "admin", "helpdesk");
    }

}
