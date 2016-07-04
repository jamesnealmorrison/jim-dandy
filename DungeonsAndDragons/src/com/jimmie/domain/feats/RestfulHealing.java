package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class RestfulHealing extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RESTFUL_HEALING;
	}

	@Override
	public String getName() {
		return "Restful Healing";
	}

	@Override
	public String getBenefit() {
		return "Maximize healing between encounters";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
