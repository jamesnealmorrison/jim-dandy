package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Wilden;

public class BurdenOfGuardianship extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BURDEN_OF_GUARDIANSHIP;
	}

	@Override
	public String getName() {
		return "Burden of Guardianship";
	}

	@Override
	public String getBenefit() {
		return "Temporary hp when you use racial power and ally is nearby";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Wilden.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
