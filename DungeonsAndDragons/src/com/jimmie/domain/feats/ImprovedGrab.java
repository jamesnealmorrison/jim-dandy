package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedGrab extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_GRAB;
	}

	@Override
	public String getName() {
		return "Improved Grab";
	}

	@Override
	public String getBenefit() {
		return "+4 to grab attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getStrength() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
