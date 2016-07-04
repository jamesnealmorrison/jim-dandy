package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class GroundingShot extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GROUNDING_SHOT;
	}

	@Override
	public String getName() {
		return "Grounding Shot";
	}

	@Override
	public String getBenefit() {
		return "Your ranged attacks hit and damage prone targets more easily";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
