package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class StrikeAndShove extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.STRIKE_AND_SHOVE;
	}

	@Override
	public String getName() {
		return "Strike and Shove";
	}

	@Override
	public String getBenefit() {
		return "Push target after critical hit with melee attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
