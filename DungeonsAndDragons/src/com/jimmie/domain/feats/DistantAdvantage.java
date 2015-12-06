package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DistantAdvantage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DISTANT_ADVANTAGE;
	}

	@Override
	public String getName() {
		return "Distant Advantage";
	}

	@Override
	public String getBenefit() {
		return "Gain combat advantage with ranged and area attacks against flanked enemies";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
