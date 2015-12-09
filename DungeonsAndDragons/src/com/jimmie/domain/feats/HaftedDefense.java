package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HaftedDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HAFTED_DEFENSE;
	}

	@Override
	public String getName() {
		return "Hafted Defense";
	}

	@Override
	public String getBenefit() {
		return "+1 AC and Reflex while wielding polearm or staff in two hands";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
