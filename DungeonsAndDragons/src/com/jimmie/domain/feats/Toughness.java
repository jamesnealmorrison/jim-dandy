package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Toughness extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.TOUGHNESS;
	}

	@Override
	public String getName() {
		return "Toughness";
	}

	@Override
	public String getBenefit() {
		return "Gain 5 additional hit points per tier";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
