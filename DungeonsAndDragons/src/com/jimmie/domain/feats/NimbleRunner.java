package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class NimbleRunner extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.NIMBLE_RUNNER;
	}

	@Override
	public String getName() {
		return "Nimble Runner";
	}

	@Override
	public String getBenefit() {
		return "+2 Reflex when you run, and don't grant combat advantage";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
