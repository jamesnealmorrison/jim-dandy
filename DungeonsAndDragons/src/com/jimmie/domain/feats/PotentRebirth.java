package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PotentRebirth extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POTENT_REBIRTH;
	}

	@Override
	public String getName() {
		return "Potent Rebirth";
	}

	@Override
	public String getBenefit() {
		return "+2 to attack and damage if reduced to 0 hp.";
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
