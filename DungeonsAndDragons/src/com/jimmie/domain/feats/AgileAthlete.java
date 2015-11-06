package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class AgileAthlete extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.AGILE_ATHLETE;
	}

	@Override
	public String getName() {
		return "Agile Athlete";
	}

	@Override
	public String getBenefit() {
		return "Roll twice with Acrobatics and Athletics checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon feat.
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
