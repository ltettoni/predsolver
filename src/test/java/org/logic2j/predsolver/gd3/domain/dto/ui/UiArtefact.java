package org.logic2j.predsolver.gd3.domain.dto.ui;

import org.logic2j.predsolver.gd3.domain.dto.core.Artefact;

public class UiArtefact extends Artefact {
	public static interface Predicate1 {
		public boolean apply(UiArtefact theArgument);
	}
}
