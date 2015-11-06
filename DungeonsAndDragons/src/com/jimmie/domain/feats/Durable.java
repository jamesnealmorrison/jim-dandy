package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Durable extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DURABLE;
	}

	@Override
	public String getName() {
		return "Durable";
	}

	@Override
	public String getBenefit() {
		return "Increase number of healing surges by 2.";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
