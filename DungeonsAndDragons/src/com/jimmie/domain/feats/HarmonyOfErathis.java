package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HarmonyOfErathis extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HARMONY_OF_ERATHIS;
	}

	@Override
	public String getName() {
		return "Harmony of Erathis";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke harmony of Erathis";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
