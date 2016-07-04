package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class PolearmFlanker extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.POLEARM_FLANKER;
	}

	@Override
	public String getName() {
		return "Polearm Flanker";
	}

	@Override
	public String getBenefit() {
		return "Flank from 1 square away when using polearm";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
