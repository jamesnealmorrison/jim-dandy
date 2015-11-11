package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CombatReflexes extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COMBAT_REFLEXES;
	}

	@Override
	public String getName() {
		return "Combat Reflexes";
	}

	@Override
	public String getBenefit() {
		return "+1 to opportunity attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getDexterity() >= 13) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
