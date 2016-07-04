package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ViciousAdvantage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.VICIOUS_ADVANTAGE;
	}

	@Override
	public String getName() {
		return "Vicious Advantage";
	}

	@Override
	public String getBenefit() {
		return "Gain combat advantage against slowed or immobilized targets";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
