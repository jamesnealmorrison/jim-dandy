package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class GreatFortitude extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GREAT_FORTITUDE;
	}

	@Override
	public String getName() {
		return "Great Fortitude";
	}

	@Override
	public String getBenefit() {
		return "+2 to Fortitude defense";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
