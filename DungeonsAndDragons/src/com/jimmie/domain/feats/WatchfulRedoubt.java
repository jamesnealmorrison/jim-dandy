package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class WatchfulRedoubt extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WATCHFUL_REDOUBT;
	}

	@Override
	public String getName() {
		return "Watchful Redoubt";
	}

	@Override
	public String getBenefit() {
		return "+1 attack when you use total defense";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
