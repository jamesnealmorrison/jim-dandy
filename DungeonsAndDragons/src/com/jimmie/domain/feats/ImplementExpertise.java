package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImplementExpertise extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPLEMENT_EXPERTISE;
	}

	@Override
	public String getName() {
		return "Implement Expertise";
	}

	@Override
	public String getBenefit() {
		return "+1 to attack rolls with chosen implement";
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
