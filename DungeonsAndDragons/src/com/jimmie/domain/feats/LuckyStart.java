package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class LuckyStart extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LUCKY_START;
	}

	@Override
	public String getName() {
		return "Lucky Start";
	}

	@Override
	public String getBenefit() {
		return "Make first attack roll twice if your initiative check is highest";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
