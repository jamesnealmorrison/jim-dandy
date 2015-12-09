package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DirectedBullRush extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DIRECTED_BULL_RUSH;
	}

	@Override
	public String getName() {
		return "Directed Bull Rush";
	}

	@Override
	public String getBenefit() {
		return "Slide your bull rush target instead of pushing";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
