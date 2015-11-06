package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedSecondWind extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_SECOND_WIND;
	}

	@Override
	public String getName() {
		return "Improved Second Wind";
	}

	@Override
	public String getBenefit() {
		return "Heal 5 additional damage with second wind";
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
