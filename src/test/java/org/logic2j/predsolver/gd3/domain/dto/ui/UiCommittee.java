package org.logic2j.predsolver.gd3.domain.dto.ui;

import org.logic2j.predsolver.gd3.domain.dto.core.Committee;

public class UiCommittee extends Committee {
	public static interface Predicate1 {
		public boolean apply(UiCommittee theArgument);
	}

}
