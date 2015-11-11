package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Rogue;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SurpriseKnockdown extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SURPRISE_KNOCKDOWN;
	}

	@Override
	public String getName() {
		return "Surprise Knockdown";
	}

	@Override
	public String getBenefit() {
		return "Knock target prone with critical hit";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 15) && (Rogue.class.isInstance(pc.getDndClass()))) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
