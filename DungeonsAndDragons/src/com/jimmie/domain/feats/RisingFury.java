package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Barbarian;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RisingFury extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RISING_FURY;
	}

	@Override
	public String getName() {
		return "Rising Fury";
	}

	@Override
	public String getBenefit() {
		return "+2 damage when you reduce enemy to 0 hp";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Barbarian.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
