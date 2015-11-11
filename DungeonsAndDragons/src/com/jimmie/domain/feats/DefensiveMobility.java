package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DefensiveMobility extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEFENSIVE_MOBILITY;
	}

	@Override
	public String getName() {
		return "Defensive Mobility";
	}

	@Override
	public String getBenefit() {
		return "+2 to AC against opportunity attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
