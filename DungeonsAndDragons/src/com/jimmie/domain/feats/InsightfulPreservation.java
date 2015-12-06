package com.jimmie.domain.feats;

import com.jimmie.domain.classes.DivineCovenant;
import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class InsightfulPreservation extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INSIGHTFUL_PRESERVATION;
	}

	@Override
	public String getName() {
		return "Insightful Preservation";
	}

	@Override
	public String getBenefit() {
		return "Preserver's rebuke grants temporary hp";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Invoker) pc.getDndClass()).getDivineCovenant() == DivineCovenant.PRESERVATION) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
