package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class HeadsmansChop extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HEADSMANS_CHOP;
	}

	@Override
	public String getName() {
		return "Headman's Chop";
	}

	@Override
	public String getBenefit() {
		return "5 extra damage to prone target when using axe or heavy blade";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
