package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class FastRunner extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FAST_RUNNER;
	}

	@Override
	public String getName() {
		return "Fast Runner";
	}

	@Override
	public String getBenefit() {
		return "+2 to speed when you charge or run";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getConstitution() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
