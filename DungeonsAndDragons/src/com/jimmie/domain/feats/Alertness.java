package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Alertness extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ALERTNESS;
	}

	@Override
	public String getName() {
		return "Alertness";
	}

	@Override
	public String getBenefit() {
		return "No combat advantage when surprised, +2 to Perception";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
