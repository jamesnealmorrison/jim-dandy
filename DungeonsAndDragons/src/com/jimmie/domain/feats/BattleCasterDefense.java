package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BattleCasterDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BATTLE_CASTER_DEFENSE;
	}

	@Override
	public String getName() {
		return "Battle Caster Defense";
	}

	@Override
	public String getBenefit() {
		return "+4 AC against opportunity attacks after you use ranged or area power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
