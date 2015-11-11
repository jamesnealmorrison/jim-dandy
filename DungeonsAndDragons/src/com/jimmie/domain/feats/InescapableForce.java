package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class InescapableForce extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INESCAPABLE_FORCE;
	}

	@Override
	public String getName() {
		return "Inescapable Force";
	}

	@Override
	public String getBenefit() {
		return "Force powers ignore insubstantial, deal additional damage";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
