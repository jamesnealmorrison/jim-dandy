package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class ImpendingVictory extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPENDING_VCTORY;
	}

	@Override
	public String getName() {
		return "Impending Victory";
	}

	@Override
	public String getBenefit() {
		return "+1 attack with at-will powers against bloodied creatures";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
