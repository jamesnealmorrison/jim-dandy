package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class PowerAttack extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POWER_ATTACK;
	}

	@Override
	public String getName() {
		return "Power Attack";
	}

	@Override
	public String getBenefit() {
		return "+2 damage in exchange for -2 to attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getStrength() >= 15) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
