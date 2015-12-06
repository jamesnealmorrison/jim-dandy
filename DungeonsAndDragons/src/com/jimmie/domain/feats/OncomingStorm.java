package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class OncomingStorm extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ONCOMING_STORM;
	}

	@Override
	public String getName() {
		return "Oncoming Storm";
	}

	@Override
	public String getBenefit() {
		return "Hit with lightning power to gain +1 on thunder attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
