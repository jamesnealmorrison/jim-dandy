package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RadiantPower extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RADIANT_POWER;
	}

	@Override
	public String getName() {
		return "Radiant Power";
	}

	@Override
	public String getBenefit() {
		return "+2 damage with implement power for -2 to attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Deva.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
