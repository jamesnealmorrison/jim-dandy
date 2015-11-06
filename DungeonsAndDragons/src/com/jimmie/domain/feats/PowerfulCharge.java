package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class PowerfulCharge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POWERFUL_CHARGE;
	}

	@Override
	public String getName() {
		return "Powerful Charge";
	}

	@Override
	public String getBenefit() {
		return "+2 damage, +2 to bull rush on a charge";
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
	}

}
