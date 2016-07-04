package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SpeedLoader extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SPEED_LOADER;
	}

	@Override
	public String getName() {
		return "Speed Loader";
	}

	@Override
	public String getBenefit() {
		return "Load crossbow as free action instead of minor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
