package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BattleHardened extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BATTLE_HARDENED;
	}

	@Override
	public String getName() {
		return "Battle Hardened";
	}

	@Override
	public String getBenefit() {
		return "+5 to saving throws against fear, +2 to initiative checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
