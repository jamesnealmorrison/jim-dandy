package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DistantShot extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DISTANT_SHOT;
	}

	@Override
	public String getName() {
		return "Distant Shot";
	}

	@Override
	public String getBenefit() {
		return "Ignore -2 penalty for long range";
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
