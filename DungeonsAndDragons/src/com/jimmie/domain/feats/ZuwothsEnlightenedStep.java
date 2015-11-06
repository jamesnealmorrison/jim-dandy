package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ZuwothsEnlightenedStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ZUWOTHS_ENLIGHTENED_STEP;
	}

	@Override
	public String getName() {
		return "Zuwoth's Enlightened Step";
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
