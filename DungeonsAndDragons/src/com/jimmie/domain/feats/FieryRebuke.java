package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Tiefling;

public class FieryRebuke extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FIERY_REBUKE;
	}

	@Override
	public String getName() {
		return "Fiery Rebuke";
	}

	@Override
	public String getBenefit() {
		return "Cause fire damage with infernal wrath";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if (Tiefling.class.isInstance(pc.getRace())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
