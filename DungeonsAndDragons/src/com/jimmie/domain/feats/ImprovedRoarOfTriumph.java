package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedRoarOfTriumph extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_ROAR_OF_TRIUMPH;
	}

	@Override
	public String getName() {
		return "Improved Roar of Triumph";
	}

	@Override
	public String getBenefit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
