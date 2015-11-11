package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ArcaneReach extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARCANE_REACH;
	}

	@Override
	public String getName() {
		return "Arcane Reach";
	}

	@Override
	public String getBenefit() {
		return "Choose square within 2 as origin with close attack power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon feat.
		if (pc.getLevel() >= 10) {
			if (pc.getDexterity() >= 15) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
