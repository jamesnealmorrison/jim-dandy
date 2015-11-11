package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BloodThirst extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BLOOD_THIRST;
	}

	@Override
	public String getName() {
		return "Blood Thirst";
	}

	@Override
	public String getBenefit() {
		return "+2 to damage against bloodied foes";
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
