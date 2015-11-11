package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BurdenOfGuardianship extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BURDEN_OF_GUARDIANSHIP;
	}

	@Override
	public String getName() {
		return "Burden of Guardianship";
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
