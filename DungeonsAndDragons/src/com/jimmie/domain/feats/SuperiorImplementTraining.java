package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SuperiorImplementTraining extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SUPERIOR_IMPLEMENT_TRAINING;
	}

	@Override
	public String getName() {
		return "Superior Implement Training";
	}

	@Override
	public String getBenefit() {
		return "Use one superior implement of a type open to your class";
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
