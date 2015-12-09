package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ShiftingDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHIFTING_DEFENSE;
	}

	@Override
	public String getName() {
		return "Shifting Defense";
	}

	@Override
	public String getBenefit() {
		return "Shift 1 square when you use total defense";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
