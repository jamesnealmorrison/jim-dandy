package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class PowerThrow extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POWER_THROW;
	}

	@Override
	public String getName() {
		return "Power Throw";
	}

	@Override
	public String getBenefit() {
		return "Trade -2 attack for damage bonus with heavy thrown weapon";
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
