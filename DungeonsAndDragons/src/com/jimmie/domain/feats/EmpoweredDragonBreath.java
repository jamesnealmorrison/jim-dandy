package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dragonborn;
import com.jimmie.domain.creatures.PlayerCharacter;

public class EmpoweredDragonBreath extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.EMPOWERED_DRAGON_BREATH;
	}

	@Override
	public String getName() {
		return "Empowered Dragon Breath";
	}

	@Override
	public String getBenefit() {
		return "Dragon breath uses d10s";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if (Dragonborn.class.isInstance(pc.getRace())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
