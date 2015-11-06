package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class FarShot extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FAR_SHOT;
	}

	@Override
	public String getName() {
		return "Far Shot";
	}

	@Override
	public String getBenefit() {
		return "Increase projectile weapon range by 5 squares";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			return true;
		}		
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
