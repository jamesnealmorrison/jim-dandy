package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class VersatileExpertise extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.VERSATILE_EXPERTISE;
	}

	@Override
	public String getName() {
		return "Versatile Expertise";
	}

	@Override
	public String getBenefit() {
		return "Bonus to attacks with weapons and implements of your choice";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
