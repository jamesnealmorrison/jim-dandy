package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Eladrin;
import com.jimmie.domain.creatures.PlayerCharacter;

public class EladrinSoldier extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ELADRIN_SOLDIER;
	}

	@Override
	public String getName() {
		return "Eladrin Soldier";
	}

	@Override
	public String getBenefit() {
		return "+2 damage and proficiency with longswords and spears";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Eladrin.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
