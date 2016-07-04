package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedMajesticWord extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_MAJESTIC_WORD;
	}

	@Override
	public String getName() {
		return "Improved Majestic Word";
	}

	@Override
	public String getBenefit() {
		return "Target of majestic word gains temporary hp";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Bard.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
