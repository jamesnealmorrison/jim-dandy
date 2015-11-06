package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DevastatingCritical extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEVASTATING_CRITICAL;
	}

	@Override
	public String getName() {
		return "Devastating Critical";
	}

	@Override
	public String getBenefit() {
		return "Deal additional 1d10 damage on a critical hit";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
