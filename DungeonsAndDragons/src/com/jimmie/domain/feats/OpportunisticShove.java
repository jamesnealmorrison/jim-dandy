package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class OpportunisticShove extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.OPPORTUNISTIC_SHOVE;
	}

	@Override
	public String getName() {
		return "Opportunistic Shove";
	}

	@Override
	public String getBenefit() {
		return "Bull rush when you make opportunity attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
