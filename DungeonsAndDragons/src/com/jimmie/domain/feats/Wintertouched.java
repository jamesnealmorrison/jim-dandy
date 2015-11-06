package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Wintertouched extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WINTERTOUCHED;
	}

	@Override
	public String getName() {
		return "Wintertouched";
	}

	@Override
	public String getBenefit() {
		return "Gain combat advantage against foe vulnerable to cold";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
