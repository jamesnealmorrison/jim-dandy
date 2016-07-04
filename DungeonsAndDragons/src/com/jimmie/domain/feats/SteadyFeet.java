package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SteadyFeet extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.STEADY_FEET;
	}

	@Override
	public String getName() {
		return "Steady Feet";
	}

	@Override
	public String getBenefit() {
		return "Ignore 1 square of difficult terrain when you walk";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
