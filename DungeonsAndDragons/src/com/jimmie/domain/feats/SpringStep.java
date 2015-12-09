package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SpringStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SPRING_STEP;
	}

	@Override
	public String getName() {
		return "Spring Step";
	}

	@Override
	public String getBenefit() {
		return "Shift 1 square when you stand up";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
