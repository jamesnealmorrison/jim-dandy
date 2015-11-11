package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class Evasion extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.EVASION;
	}

	@Override
	public String getName() {
		return "Evasion";
	}

	@Override
	public String getBenefit() {
		return "No damage from missed area or close attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
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
