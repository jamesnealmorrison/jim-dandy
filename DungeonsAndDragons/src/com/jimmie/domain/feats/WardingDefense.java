package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class WardingDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WARDING_DEFENSE;
	}

	@Override
	public String getName() {
		return "Warding Defense";
	}

	@Override
	public String getBenefit() {
		return "Allies benefit when you use total defense and wield a shield";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
