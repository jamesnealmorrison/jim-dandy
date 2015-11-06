package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class MountedCombat extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MOUNTED_COMBAT;
	}

	@Override
	public String getName() {
		return "Mounted Combat";
	}

	@Override
	public String getBenefit() {
		return "Gain access to the special abilities of your mount";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
