package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DeadlyAxe extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEADLY_AXE;
	}

	@Override
	public String getName() {
		return "Deadly Axe";
	}

	@Override
	public String getBenefit() {
		return "Treat all axes as high crit weapons";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if ((pc.getStrength() >= 17) && (pc.getConstitution() >= 13)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
