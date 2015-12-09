package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CombatIntuition extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COMBAT_INTUITION;
	}

	@Override
	public String getName() {
		return "Combat Intuition";
	}

	@Override
	public String getBenefit() {
		return "+2 to opportunity attacks against enemy you miss";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
