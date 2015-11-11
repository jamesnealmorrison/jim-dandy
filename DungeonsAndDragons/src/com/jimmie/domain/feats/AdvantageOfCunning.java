package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class AdvantageOfCunning extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ADVANTAGE_OF_CUNNING;
	}

	@Override
	public String getName() {
		return "Advantage of Cunning";
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
