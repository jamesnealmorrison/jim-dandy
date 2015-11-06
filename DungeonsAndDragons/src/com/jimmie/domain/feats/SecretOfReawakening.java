package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SecretOfReawakening extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SECRET_OF_REAWAKENING;
	}

	@Override
	public String getName() {
		return "Secret of Reawakening";
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
