package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class GorebruteCharge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GOREBRUTE_CHARGE;
	}

	@Override
	public String getName() {
		return "Gorebrute Charge";
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
