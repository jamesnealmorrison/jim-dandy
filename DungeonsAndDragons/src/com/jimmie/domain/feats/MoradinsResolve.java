package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class MoradinsResolve extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MORADINS_RESOLVE;
	}

	@Override
	public String getName() {
		return "Moradin's Resolve";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Moradin's resolve";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
