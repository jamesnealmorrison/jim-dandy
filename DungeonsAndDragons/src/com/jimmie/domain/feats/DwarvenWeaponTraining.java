package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dwarf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DwarvenWeaponTraining extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DWARVEN_WEAPON_TRAINING;
	}

	@Override
	public String getName() {
		return "Dwarven Weapon Training";
	}

	@Override
	public String getBenefit() {
		return "+2 damage and proficiency with axes and hammers";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Dwarf.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
